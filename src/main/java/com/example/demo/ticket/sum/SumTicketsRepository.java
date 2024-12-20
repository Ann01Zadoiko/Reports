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
    Integer sumTickets(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram = tr.id " +
                    "where t.day = :day and t.price=15 and tr.depo = :depo")
    Integer sumTicketsByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.price=15 and tr.depo=:depo " +
                    "and tr.number_of_tram=:numberOfTram")
    Integer sumTicketsByTram(LocalDate day, String depo, @Param("numberOfTram") String numberOfTram);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t join tracks tr on t.id_track = tr.id " +
                    "join trams tm on t.id_tram=tm.id where depo=:depo " +
                    "and tr.day=t.day and tr.day=:day and t.price=15 and " +
                    "((tr.time is null and tr.first_part=:track) or " +
                    "(tr.time>t.time and tr.first_part=:track ) or " +
                    "(tr.time<t.time and tr.second_part=:track) )")
    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);

    @Query(nativeQuery = true,
            value = "SELECT sum(t.price) " +
                    "FROM tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "join tracks tc on t.id_track=tc.id " +
                    "where EXTRACT(YEAR FROM t.day)=:year and EXTRACT(MONTH FROM t.day)=:year " +
                    "and t.price=15 and tr.depo=:depo and tc.first_part=:track")
    Integer sumTicketsByDepoMonthTrack(String month,String year, String depo, String track);

    @Query(nativeQuery = true,
            value = "SELECT sum(t.price) " +
                    "FROM tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "join tracks tc on t.id_track=tc.id " +
                    "where EXTRACT(YEAR FROM t.day)=:year and EXTRACT(MONTH FROM t.day)=:year " +
                    "and t.price=15 and tr.depo=:depo")
    Integer sumTicketsByDepoMonth(String month,String year, String depo);

}
