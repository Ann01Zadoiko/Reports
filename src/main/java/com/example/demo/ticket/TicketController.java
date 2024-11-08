package com.example.demo.ticket;

import com.example.demo.importData.ImportTickets;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/tickets")
public class TicketController {

    private final ImportTickets importTickets;

    @GetMapping("/")
    public String showUploadForm() {
        return "/tickets/index";
    }

    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.getOriginalFilename().endsWith(".xls")){
            importTickets.importExcelToDataXls(file,null,null);
        } else {
            importTickets.importExcelToDataXlsx(file,null,null);
        }

        return "/v1/tickets?success";
    }
}
