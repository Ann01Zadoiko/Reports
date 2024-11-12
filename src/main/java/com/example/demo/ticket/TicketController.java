package com.example.demo.ticket;

import com.example.demo.importData.ImportTickets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/tickets")
public class TicketController {

    private final ImportTickets importTickets;

    //отображение страницы
    @GetMapping
    public String showUploadForm() {
        return "/tickets/index";
    }

    //загрузка файла
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        importTickets.importExcelToData(file, null, null);
        return "redirect:/v1/tickets?success";
    }
}
