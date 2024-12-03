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
    public int sumTickets(LocalDate day) {
        return repository.sumTickets(day);
    }

    //sum of tickets by day and depo
    @Override
    public int sumTicketsByDepo(LocalDate day, String depo) {
        return repository.sumTicketsByDepo(day, depo);
    }

    //sum of tickets by day and the tram
    @Override
    public int sumTicketsByTram(LocalDate day, Tram tram) {
        return repository.sumTicketsByTram(day, tram.getDepo(), tram.getNumberOfTram());
    }

    //sum of tickets by day, depo and track
    @Override
    public Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track) {
        Integer sum = repository.sumTicketsByDepoAndTrack(day, depo, track);
        return sum != null ? sum : 0;
    }
}
