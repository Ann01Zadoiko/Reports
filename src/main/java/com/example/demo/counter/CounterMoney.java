package com.example.demo.counter;

import com.example.demo.ticket.TicketService;
import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
public class CounterMoney implements Counter{

    private final TicketService ticketService;
    private final CounterTickets counterTickets;

    @Override
    public int countByDayGeneral(String dayString) {
        int i = counterTickets.countByDayGeneral(dayString);

        return i*7;
    }

    @Override
    public Map<Tram, Integer> countMapOfTram(LocalDate day, int amount) {
        return countMapOfTram(day, 7);
    }
}
