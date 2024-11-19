package com.example.demo.download;

import com.example.demo.sheet.SheetMain;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class DownloadController {

    private final TicketService ticketService;
    private String downloadFile;
    private final SheetMain sheetMain;

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
        downloadFile = sheetMain.createWorkbook(day);

        return "redirect:/v1/reports/download";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() {

        File file = new File(downloadFile);
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
