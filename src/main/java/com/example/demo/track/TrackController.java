package com.example.demo.track;


import com.example.demo.importData.ImportTracks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final ImportTracks importTracks;
    private final TrackService trackService;

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


}
