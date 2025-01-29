package com.example.demo.ticket;

import com.example.demo.tram.Tram;

import java.time.LocalDate;
import java.util.List;

public interface ITicketService {

    void add(Ticket ticket);

    List<Ticket> getByDay(LocalDate day);

    List<LocalDate> getLocalDate();

    List<LocalDate> getDaysOfMoth(String month, String year);

    List<Tram> findListOfTramWithoutTrack(LocalDate day, String depo);

    List<Tram> findTramsByDayAndDepo(LocalDate day, String depo);
}
