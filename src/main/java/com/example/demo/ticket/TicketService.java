package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TickerRepository tickerRepository;

    public void add(Ticket ticket){
        tickerRepository.save(ticket);
    }

    public List<Ticket> getByTramAndDay(Tram tram, LocalDate day){
        return tickerRepository.findByTramAndDay(tram, day);
    }

    public void deleteAllTickets(){
        tickerRepository.deleteAll();
    }

    public List<Ticket> getAll(){
        return tickerRepository.findAll();
    }

    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

    public List<Ticket> getByTram(Tram tram){
        return tickerRepository.findByTram(tram);
    }

    public List<LocalDate> getLocalDate(){
        return tickerRepository.findDistinctDays();
    }

}
