package com.example.demo.ticket.sum;


import com.example.demo.tram.Tram;

import java.time.LocalDate;

public interface ISumTicketsService {

    int sumTickets(LocalDate day);

    int sumTicketsByDepo(LocalDate day, String depo);

    int sumTicketsByTram(LocalDate day, Tram tram);

    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);
}
