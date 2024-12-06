package com.example.demo.track;


import com.example.demo.importData.ImportTracks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}
