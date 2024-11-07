package com.example.demo.track;

import com.example.demo.combine.Combine;
import com.example.demo.importData.ImportTracks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final ImportTracks importTracks;
    private final Combine combine;

    @GetMapping("/")
    public String showUploadForm(){
        return "/tracks/upload";
    }

    @PostMapping("/")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("day") LocalDate day,
                         @RequestParam("depo") String depo){

        if (file.getOriginalFilename().endsWith(".xls")){
            importTracks.importExcelToDataXls(file, depo, day);
        } else {
            importTracks.importExcelToDataXlsx(file, depo, day);
        }

        combine.combineLong(day);

        return "redirect:/v1/tracks";
    }
}
