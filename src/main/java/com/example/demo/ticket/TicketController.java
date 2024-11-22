package com.example.demo.ticket;

import com.example.demo.file.FileTicketService;
import com.example.demo.importData.ImportTickets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/tickets")
public class TicketController {

    private final ImportTickets importTickets;
    private final TicketService ticketService;
    private final FileTicketService fileTicketService;

    //отображение страницы
    @GetMapping
    public String showUploadForm(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM(MMMM).yyyy");
        List<LocalDate> days = ticketService.getLocalDate();
        List<String> list = days.stream().map(date -> date.format(formatter)).toList();

        model.addAttribute("days", list);

        return "/tickets/index";
    }

    //загрузка файла
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (fileTicketService.isExists(file.getName())){
            return "redirect:/v1/tickets?error";
        }

        importTickets.importExcelToData(file, null, null);
        return "redirect:/v1/tickets?success";
    }
}
