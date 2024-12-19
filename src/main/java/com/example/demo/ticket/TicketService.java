package com.example.demo.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TickerRepository tickerRepository;

    //add new ticket
    public void add(Ticket ticket){
        tickerRepository.save(ticket);
    }

    //get a list of tickets by day
    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

    //get a unique list of tickets by day
    public List<LocalDate> getLocalDate(){
        return tickerRepository.findDistinctDays();
    }

    public List<LocalDate> getDaysOfMoth(String month, String year){
        return tickerRepository.findDaysOfMonth(year, month);
    }

}
