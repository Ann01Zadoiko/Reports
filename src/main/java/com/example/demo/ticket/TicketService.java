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

    public int sumByTravelCardAndDay(LocalDate day, String travelCard){
        Integer sum = tickerRepository.sumByTravelCardAndDay(day, travelCard);
        return sum != null ? sum : 0;
    }

    public int countByTravelCardAndDay(LocalDate day, String travelCard){
        Integer count = tickerRepository.countByTravelCardAndDay(day, travelCard);
        return count != null ? count : 0;
    }

    public int countTravelCards(LocalDate day){
        return tickerRepository.countTravelCard(day);
    }

    public int sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo){
        Integer sum = tickerRepository.sumByTravelCardAndDayAndDepo(day, travelCard, depo);
        return sum != null ? sum : 0;
    }

    public int countByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo){
        Integer count = tickerRepository.countByTravelCardAndDayAndDepo(day, travelCard, depo);
        return count != null ? count : 0;
    }

    public int countTravelCardDepo(LocalDate day, String depo){
        return tickerRepository.countTravelCardDepo(day, depo);
    }

    public int sumTravelCardDepo(LocalDate day, String depo){
        return tickerRepository.sumTravelCardDepo(day, depo);
    }
}
