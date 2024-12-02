package com.example.demo.ticket.sum;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SumTicketService implements ISumTicketsService {

    private final SumTicketsRepository repository;

    @Override
    public int sumTickets(LocalDate day) {
        return repository.sumTickets(day);
    }

    @Override
    public int sumTicketsByDepo(LocalDate day, String depo) {
        return repository.sumTicketsByDepo(day, depo);
    }

    @Override
    public int sumTicketsByTram(LocalDate day, Tram tram) {
        return repository.sumTicketsByTram(day, tram.getDepo(), tram.getNumberOfTram());
    }

    @Override
    public Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track) {
        Integer sum = repository.sumTicketsByDepoAndTrack(day, depo, track);
        return sum != null ? sum : 0;
    }
}
