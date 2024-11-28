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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final ImportTracks importTracks;

    //отображение страницы
    @GetMapping
    public String showUploadForm(){
        return "/tracks/upload";
    }

    //загрузка файла
    @PostMapping
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("day") LocalDate day,
            @RequestParam("depo") String depo) {

        importTracks.importExcelToData(file, depo, day);

        return "redirect:/v1/tracks?success";
    }

}
