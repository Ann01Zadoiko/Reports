package com.example.demo.combine;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import com.example.demo.track.Track;
import com.example.demo.track.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Combine {

    private final TicketService ticketService;
    private final TrackService trackService;

    //добавить значение id_track в таблицу tickets по дате
    public void combineLong(LocalDate day){
        List<Ticket> tickets = ticketService.getByDay(day);
        List<Track> tracks = trackService.getByDay(day);

        for (Ticket ticket: tickets){
            for (Track track: tracks){
                //добавить значение id_track в соответсвующий ticket
                if (track.getTram().equals(ticket.getTram())){
                    ticket.setTrack(track);
                    ticketService.add(ticket);
                }
            }
        }
        log.info("Tracks have added to tickets");

    }
}
