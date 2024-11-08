package com.example.demo.counter;

import com.example.demo.ticket.TicketService;
import lombok.RequiredArgsConstructor;
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
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class CounterController {

    private final CounterTickets counterTickets;
    private final TicketService ticketService;
    private String downloadFile;

    @GetMapping("/")
    public String showDownloadPage(Model model){
        System.out.println(ticketService.getLocalDate().get(0));

        model.addAttribute("days", ticketService.getLocalDate());
        return "/tickets/download";
    }

    @SneakyThrows
    @PostMapping("/")
    public String download(@RequestParam("day") LocalDate day){

        downloadFile = counterTickets.createTable(day);
        return "redirect:/v1/reports/download";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        // Используем FileSystemResource для работы с файлами, которые находятся на диске
        File file = new File(downloadFile); // Предполагается, что downloadFile содержит полный путь к файлу
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

        System.out.println("File path: " + downloadFile);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
