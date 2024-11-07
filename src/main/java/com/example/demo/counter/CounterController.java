package com.example.demo.counter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/download")
public class CounterController {

    private final CounterTickets counterTickets;

    @GetMapping("/")
    public String showDownloadPage(){
        return "/tickets/download";
    }

    @SneakyThrows
    @PostMapping("/")
    public String download(@RequestParam("day") LocalDate day){

        counterTickets.createTable(day);
        return "redirect:/v1/download";
    }
}
