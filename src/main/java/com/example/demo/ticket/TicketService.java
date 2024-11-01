package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import com.example.demo.tram.TramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TickerRepository tickerRepository;
    private final TramRepository tramRepository;

    public void add(LocalDate day, Tram tram){
        Ticket ticket = new Ticket();
        ticket.setDay(day);
        ticket.setTram(tram);

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

    public void getByDepo(String depo){
        List<Tram> trams = tramRepository.findByDepo(depo);


    }
}
