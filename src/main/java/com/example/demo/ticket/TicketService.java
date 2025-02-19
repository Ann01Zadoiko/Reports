package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
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
        List<LocalDate> distinctDays = tickerRepository.findDistinctDays();// По возрастанию;
        Collections.sort(distinctDays);
        return distinctDays;
    }

    @Override
    public List<LocalDate> getDaysOfMoth(String month, String year){
        List<LocalDate> daysOfMonth = tickerRepository.findDaysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        Collections.sort(daysOfMonth);
        return daysOfMonth;
    }

    @Override
    public List<Tram> findListOfTramWithoutTrack(LocalDate day, String depo){
        return tickerRepository.findListOfTramWithoutTrack(day, depo);
    }

    @Override
    public List<Tram> findTramsByDayAndDepo(LocalDate day, String depo) {
        return tickerRepository.findTramsByDayAndDepo(day, depo);
    }
}
