package com.example.demo.track;


import com.example.demo.combine.Combine;
import com.example.demo.excel.constance.Depo;
import com.example.demo.importData.ImportTracks;
import com.example.demo.ticket.TicketService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final ImportTracks importTracks;
    private final TrackService trackService;
    private final TramService tramService;
    private final TicketService ticketService;
    private final Combine combine;

    //show a page for uploading a file
    @GetMapping
    public String showUploadForm(Model model){
        List<Object[]> dayAndDepoList = trackService.getDayAndDepo();
        model.addAttribute("dayAndDepoList", dayAndDepoList);

        // Получение повторяющихся записей
        List<Track> duplicateTracks = trackService.findDuplicateTracksDetailed();
        model.addAttribute("duplicateTracks", duplicateTracks);

        return "/tracks/upload";
    }

    //upload a file
    @PostMapping
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("day") LocalDate day,
            @RequestParam("depo") String depo) {

        importTracks.importExcelToData(file, depo, day);

        return "redirect:/v1/tracks?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        trackService.deleteById(id);
        return "redirect:/v1/tracks";
    }

    @GetMapping("/add")
    public String add(){
        return "/tracks/add";
    }

    //add new user
    @PostMapping("/add")
    public String save(@RequestParam("numberOfTram") String numberOfTram,
                       @RequestParam("depo") String depo,
                       @RequestParam("day") LocalDate day,
                       @RequestParam("firstPart") String firstPart,
                       @RequestParam(value = "time", required = false)  @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                       @RequestParam("secondPart") String secondPart
    ){

        Tram tram = tramService.getByDepoAndNumberOfTram(depo, numberOfTram);
        Track track = new Track();
        track.setDay(day);
        track.setTram(tram);
        track.setFirstPart(firstPart);

        if (time != null){
            track.setTime(time);
            track.setSecondPart(secondPart);
        }

        trackService.add(track);
        return "redirect:/v1/tracks/add?success"; //?success
    }

    @GetMapping("/check")
    public String showCheck(Model model){
        return "tracks/check";
    }

    @PostMapping("/check")
    public String getListOfTramsWithoutTrack(@RequestParam("depo") String depo,
                                             @RequestParam("day") LocalDate day,
                                             Model model){

        combine.combineTrackAndTicketsByDay(day);
        model.addAttribute("trams", ticketService.findListOfTramWithoutTrack(day, depo));
        return "tracks/check";
    }


    @GetMapping("/addMultiple")
    public String showDepoAndDateForm() {
        return "tracks/addMultiple";
    }

    @PostMapping("/addMultiple")
    public String showTrackForm(@RequestParam("day") LocalDate day,
                                @RequestParam("depo") String depo,
                                Model model) {
        List<Tram> trams = ticketService.findTramsByDayAndDepo(day, depo);
        List<Track> tracks = trams.stream()
                .map(tram -> {
                    Track track = new Track();
                    track.setSecondPart("");
                    track.setTram(tram);
                    track.setFirstPart("");
                    track.setDay(day);
                    track.setTime(null);
                    return track;
                })
                .collect(Collectors.toList());

        model.addAttribute("tracks", tracks);
        model.addAttribute("day", day);
        model.addAttribute("depo", depo);
        return "tracks/fill";
    }

    @PostMapping("/saveMultiple")
    public String saveTracks(@RequestParam("day") LocalDate day,
                             @RequestParam("depo") String depo,
                             @RequestParam("firstPart") List<String> firstParts,
                             @RequestParam(value = "time", required = false) List<LocalTime> times,
                             @RequestParam(value = "secondPart", required = false) List<String> secondParts,
                             @RequestParam("numberOfTram") List<String> tramNumbers) {

        List<Track> tracks = new ArrayList<>();
        for (int i = 0; i < tramNumbers.size(); i++) {
            Tram tram = tramService.getByDepoAndNumberOfTram(depo,tramNumbers.get(i));
            LocalTime time = (times != null && i < times.size()) ? times.get(i) : null;
            String secondPart = (secondParts != null && i < secondParts.size()) ? secondParts.get(i) : null;

            Track track = new Track();
            track.setTram(tram);
            track.setTime(time);
            track.setDay(day);
            track.setFirstPart(firstParts.get(i));
            track.setSecondPart(secondPart);
            tracks.add(track);
        }

        trackService.addAll(tracks);
        return "redirect:/v1/tracks/saveMultiple?success";
    }
}




