package com.example.demo.download;

import com.example.demo.excel.workbook.WorkbookForMonth;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;

@Controller
@RequestMapping("/v1/month")
public class DownloadForMonthController {

    private String downloadFile;
    private final WorkbookForMonth workbook;

    public DownloadForMonthController(WorkbookForMonth workbook) {
        this.workbook = workbook;
    }

    //show a page and available dates
    @GetMapping("/")
    public String showDownloadPage(Model model){

        return "/tickets/downloadMonth";
    }

    //create a file by a day
    @SneakyThrows
    @PostMapping("/")
    public String download(@RequestParam("month") String month,
                           @RequestParam("year") String year,
                           @RequestParam("depo") String depo){


        downloadFile = workbook.createWorkbook(month, year, depo);

        return "redirect:/v1/month/download";
    }

    //download a file in directory (downloaded)
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(HttpServletResponse response) {
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=\"example.xlsx\"");
//        response.setCharacterEncoding("UTF-8");

        File file = new File(downloadFile);
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

        //success
        //error

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
