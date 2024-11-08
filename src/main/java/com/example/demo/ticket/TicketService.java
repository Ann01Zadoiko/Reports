package com.example.demo.ticket;

import com.example.demo.track.Track;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TickerRepository tickerRepository;
    private final TramRepository tramRepository;

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

    public void getByDepo(String depo){
        List<Tram> trams = tramRepository.findByDepo(depo);

    }

    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

//    public Set<Tram> getTramsByDay(LocalDate day){
//        return tickerRepository.findTramsByDay(day);
//    }

    public List<Ticket> getByTram(Tram tram){
        return tickerRepository.findByTram(tram);
    }

    public List<LocalDate> getLocalDate(){
        return tickerRepository.findDistinctDays();
    }

}
