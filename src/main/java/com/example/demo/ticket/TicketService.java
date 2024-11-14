package com.example.demo.ticket;

import com.example.demo.track.Track;
import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<Ticket> getByDay(LocalDate day){
        return tickerRepository.findByDay(day);
    }

    public List<Ticket> getByTram(Tram tram){
        return tickerRepository.findByTram(tram);
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

    public List<Ticket> getByDayAndPrice(LocalDate day){
        return tickerRepository.findByDayAndPrice(day, 7);
    }

    public int sumTravelCardByDepo(LocalDate day, String depo){
        return tickerRepository.sumTravelCardByDepo(day, depo);
    }


    public int sumTicketsFullDay(LocalDate day, Track track){
        if (!track.getTime().equals(null)){
            return 0;
        } else {
            return tickerRepository.sumTicketsFullDay(day);
        }
    }

    public int sumTicketsFirstPart(LocalDate day, LocalTime time){
        return tickerRepository.sumTicketsFirstPart(day, time);
    }

    public int sumTicketsSecondPart(LocalDate day, LocalTime time){
        return tickerRepository.sumTicketsSecondPart(day, time);
    }

    public List<Ticket> getTravelCards(LocalDate day){
        return tickerRepository.findTravelCard(day);
    }
}
