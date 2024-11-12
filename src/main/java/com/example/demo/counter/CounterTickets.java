package com.example.demo.counter;

import com.example.demo.combine.Combine;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CounterTickets{

    private final TicketService ticketService;
    private final TramService tramService;
    private final TrackService trackService;
    private final Combine combine;

    public int countByDayGeneral(String dayString){
        LocalDate day = LocalDate.parse(dayString);

        return ticketService.getByDay(day).size();
    }

    public Map<Tram, Integer> countMapOfTram(LocalDate day, int amount) {

        combine.combineLong(day);

        Map<Tram, Integer> map = new HashMap<>();

        for (Tram tram: tramService.getByDay(day)){
            map.put(tram, ticketService.getByTram(tram).size() * amount);
            System.out.println(tram.getDepo() + " " + tram.getNumberOfTram() + ": " + ticketService.getByTram(tram).size());
        }

        return map;
    }

}
