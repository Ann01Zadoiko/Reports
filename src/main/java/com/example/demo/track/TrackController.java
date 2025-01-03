package com.example.demo.track;


import com.example.demo.importData.ImportTracks;
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
import java.util.List;

@Controller
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final ImportTracks importTracks;
    private final TrackService trackService;
    private final TramService tramService;

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
        return "redirect:/v1/tracks";
    }

}
