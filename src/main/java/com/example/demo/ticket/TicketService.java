package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService{

    private final TickerRepository tickerRepository;

    //add new ticket
    @Override
    public void add(Ticket ticket){
        tickerRepository.save(ticket);
    }

    //get a list of tickets by day
    @Override
    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

    //get a unique list of tickets by day
    @Override
    public List<LocalDate> getLocalDate(){
        return tickerRepository.findDistinctDays();
    }

    @Override
    public List<LocalDate> getDaysOfMoth(String month, String year){
        return tickerRepository.findDaysOfMonth(year, month);
    }

    @Override
    public List<Tram> findListOfTramWithoutTrack(LocalDate day, String depo){
        return tickerRepository.findListOfTramWithoutTrack(day, depo);
    }
}
