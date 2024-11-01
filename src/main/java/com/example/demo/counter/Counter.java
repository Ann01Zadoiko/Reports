package com.example.demo.counter;

import com.example.demo.ticket.Ticket;
import com.example.demo.ticket.TicketService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Counter {

    private final TicketService ticketService;

//    public int countByMonth(String month, String depo){
//        List<Ticket> tickets = ticketService.getAll();
//        int count = 0;
//
//        for(Ticket ticket: tickets){
//            if(ticket.getDay().getMonth().equals(){
//                count++;
//            }
//        }
//        return count;
//    }

//    public int countByDay(LocalDate day, String depo){
//        List<Ticket> tickets;
//        int count = 0;
//
//        if (depo.equals("general")){
//            tickets = ticketService.getAll();
//        } else {
//            tickets = ticketService.
//        }
//
//        for (Ticket ticket: tickets){
//            if (ticket.getDay().equals(day)){
//                count++;
//            }
//        }
//        return count;
//    }
}
