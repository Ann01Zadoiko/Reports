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

    private final TicketService ticketService;
    private final TramService tramService;

    @GetMapping("/upload")
    public String add (@ModelAttribute("file") MultipartFile file){
        return "/index";
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file){
        new ImportTickets(tramService,ticketService).importExcelToData(file);
        return "redirect:/v1/tickets/upload";
    }


}
