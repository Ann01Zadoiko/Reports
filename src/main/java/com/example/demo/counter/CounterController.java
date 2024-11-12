package com.example.demo.counter;

import com.example.demo.exportData.ExportData;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class CounterController {

    private final CounterTickets counterTickets;
    private final TicketService ticketService;
    private String downloadFile;
    private final ExportData exportData;

    @GetMapping("/")
    public String showDownloadPage(Model model){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM(MMMM).yyyy");
        List<LocalDate> days = ticketService.getLocalDate();
        List<String> list = days.stream().map(date -> date.format(formatter)).toList();

        model.addAttribute("days", list);
        return "/tickets/download";
    }

    @SneakyThrows
    @PostMapping("/")
    public String download(@RequestParam("day") LocalDate day){

        downloadFile = exportData.createTable(day);
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
