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

    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

    public List<LocalDate> getLocalDate(){
        return tickerRepository.findDistinctDays();
    }

    public int sumTickets(LocalDate day){
        return tickerRepository.sumTickets(day);
    }

    public int sumTravelCard(LocalDate day){
        return tickerRepository.sumTravelCard(day);
    }

    public int sumTicketsByDepo(LocalDate day, String depo){
        return tickerRepository.sumTicketsByDepo(day, depo);
    }

    public int sumTicketsByTram(LocalDate day, Tram tram){
        return tickerRepository.sumTicketsByTram(day, tram.getDepo(), tram.getNumberOfTram());
    }

    public int sumTravelCardByDepo(LocalDate day, String depo){
        return tickerRepository.sumTravelCardByDepo(day, depo);
    }

    public List<Ticket> getTravelCards(LocalDate day){
        return tickerRepository.findTravelCard(day);
    }

    public int sumByDepoAndTrack(LocalDate day, String depo, String track){
        Integer sum = tickerRepository.sumTicketsByDepoAndTrack(day, depo, track);
        return sum != null ? sum : 0;
    }
}
