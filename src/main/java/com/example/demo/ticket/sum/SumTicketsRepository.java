package com.example.demo.ticket.sum;

import com.example.demo.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SumTicketsRepository extends JpaRepository<Ticket, Long> {

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day =:day and t.price=15")
    int sumTickets(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram = tr.id " +
                    "where t.day = :day and t.price=15 and tr.depo = :depo")
    int sumTicketsByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.price=15 and tr.depo=:depo " +
                    "and tr.number_of_tram=:numberOfTram")
    int sumTicketsByTram(LocalDate day, String depo, @Param("numberOfTram") String numberOfTram);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t join tracks tr on t.id_track = tr.id " +
                    "join trams tm on t.id_tram=tm.id where depo=:depo " +
                    "and tr.day=t.day and tr.day=:day and t.price=15 and " +
                    "((tr.time is null and tr.first_part=:track) or " +
                    "(tr.time>t.time and tr.first_part=:track ) or " +
                    "(tr.time<t.time and tr.second_part=:track) )")
    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);

}
