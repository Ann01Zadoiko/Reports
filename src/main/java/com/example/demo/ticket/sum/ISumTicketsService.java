package com.example.demo.ticket.sum;


import com.example.demo.tram.Tram;

import java.time.LocalDate;

public interface ISumTicketsService {

    Integer sumTickets(LocalDate day);

    Integer sumTicketsByDepo(LocalDate day, String depo);

    Integer sumTicketsByTram(LocalDate day, Tram tram);

    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);

    Integer sumTicketsByDepoMonthTrack(String month,String year, String depo, String track);

    Integer sumTicketsByDepoMonth(String month,String year, String depo);
}
