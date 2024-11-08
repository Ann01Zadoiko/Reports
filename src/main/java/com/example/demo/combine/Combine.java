package com.example.demo.combine;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import com.example.demo.tram.Tram;
import com.example.demo.tram.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Combine {

    private final TicketService ticketService;
    private final TrackService trackService;

    public void combineLong(LocalDate day){
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
    }
}
