package com.example.demo.ticket.sum;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SumTicketService implements ISumTicketsService {

    private final SumTicketsRepository repository;

    //sum of tickets by day
    @Override
    public Integer sumTickets(LocalDate day) {
        Integer sum = repository.sumTickets(day);
        return sum != null ? sum : 0;
    }

    //sum of tickets by day and depo
    @Override
    public Integer sumTicketsByDepo(LocalDate day, String depo) {
        Integer sum = repository.sumTicketsByDepo(day, depo);
        return sum != null ? sum : 0;
    }

    //sum of tickets by day and the tram
    @Override
    public Integer sumTicketsByTram(LocalDate day, Tram tram) {
        Integer sum = repository.sumTicketsByTram(day, tram.getDepo(), tram.getNumberOfTram());
        return sum != null ? sum : 0;
    }

    //sum of tickets by day, depo and track
    @Override
    public Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track) {
        Integer sum = repository.sumTicketsByDepoAndTrack(day, depo, track);
        return sum != null ? sum : 0;
    }
}
