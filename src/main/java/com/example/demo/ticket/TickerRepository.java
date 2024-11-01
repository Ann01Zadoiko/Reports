package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findByTramAndDay(Tram tram, LocalDate day);

    List<Ticket> findByDay(LocalDate day);


    @Query("from Ticket t join Tram tr where tr.depo=?")
    List<Ticket> findByDepo(String depo);

    //count by day
    //count by month
    //...
//    List<Ticket> countTicketsByDayAndTram(LocalDate day, Tram tram);
//    List<Ticket> countTicketsByDay(LocalDate day);
//    List<Ticket> countTicketsByDayAndDepo(LocalDate day, String depo);

    //sort by id depo
    //        tram
    //        day
    //        track

}
