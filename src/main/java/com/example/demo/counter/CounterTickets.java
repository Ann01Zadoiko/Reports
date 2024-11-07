package com.example.demo.counter;

import com.example.demo.ticket.TicketService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class CounterTickets implements Counter{

    private final TicketService ticketService;
    private final TramService tramService;


    //use
    @Override
    public int countByDayGeneral(String dayString){
        LocalDate day = LocalDate.parse(dayString);

        return ticketService.getByDay(day).size();
    }

    public int countByDayAndTram(Tram tram, LocalDate day){
        return ticketService.getByTramAndDay(tram, day).size();
    }


    //use
    @Override
    public Map<Tram, Integer> countMapOfTram(LocalDate day, int amount) {
        Map<Tram, Integer> map = new HashMap<>();

        for (Tram tram: tramService.getByDay(day)){
            map.put(tram, ticketService.getByTram(tram).size() * amount);
        }

        return map;
    }
}
