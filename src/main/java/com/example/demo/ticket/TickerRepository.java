package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findByTramAndDay(Tram tram, LocalDate day);

    List<Ticket> findByDay(LocalDate day);

 //   @Query(nativeQuery = true, value = "select tr.* from reports.tickets t join reports.trams tr on t.id_tram=tr.id where tr.depo= :")
  //  Set<Tram> findTramsByDay(LocalDate day);

    List<Ticket> findByTram(Tram tram);


 //   @Query("from Ticket t join Tram tr where tr.depo=?")
  //  List<Ticket> findByDepo(String depo);

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
