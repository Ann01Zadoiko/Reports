package com.example.demo.combine;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class Combine implements ICombine{

    private final TicketService ticketService;
    private final TrackService trackService;

    //add id_track (value) into tickets (table) by date
    @Override
    public void combineTrackAndTicketsByDay(LocalDate day){
        List<Ticket> tickets = ticketService.getByDay(day);
        List<Track> tracks = trackService.getByDay(day);

        for (Ticket ticket: tickets){
            for (Track track: tracks){
                if (track.getTram().equals(ticket.getTram())){
                    ticket.setTrack(track);
                    ticketService.add(ticket);
                }
            }
        }
        log.info("Tracks have added to tickets");
    }

}
