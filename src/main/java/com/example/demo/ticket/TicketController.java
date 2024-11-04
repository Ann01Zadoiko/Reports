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


//    @GetMapping("/upload")
//    public String add (@ModelAttribute("file") MultipartFile file){
//        return "/index";
//    }
//
//    @PostMapping("/upload")
//    public String upload(@RequestPart("file") MultipartFile file){
//        new ImportTickets(tramService,ticketService).importExcelToData(file);
//        return "redirect:/v1/tickets/upload";
//    }

    private final ImportTickets importTickets;

    @GetMapping("/")
    public String showUploadForm() {
        return "index";
    }

    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/v1/tickets?error";
        }

       // if (file.getName().endsWith(".xls")){
        if (file.getOriginalFilename().endsWith(".xls")){
            importTickets.importExcelToDataXls(file);
        } else {
            importTickets.importExcelToData(file);
        }


        //importTickets.importExcelToData(file);
        return "redirect:/v1/tickets?success";
    }
}
