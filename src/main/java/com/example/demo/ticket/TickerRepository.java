package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTramAndDay(Tram tram, LocalDate day);

    List<Ticket> findByDay(LocalDate day);

    List<Ticket> findByTram(Tram tram);

    List<Ticket> findByDayAndPrice(LocalDate day, Integer price);

    @Query("SELECT DISTINCT t.day FROM Ticket t")
    List<LocalDate> findDistinctDays();

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day =:day and t.price=7")
    int sumTickets(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day = :day and t.price>7")
    int sumTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) \n" +
            "from tickets t \n" +
            "join trams tr on t.id_tram = tr.id \n" +
            "where t.day = :day and t.price=7 and tr.depo = :depo")
    int sumTicketsByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) \n" +
                    "from tickets t\n" +
                    "join trams tr on t.id_tram=tr.id\n" +
                    "where t.day=:day and t.price=7 and tr.depo=:depo and tr.number_of_tram=:numberOfTram")
    int sumTicketsByTram(LocalDate day, String depo, @Param("numberOfTram") String numberOfTram);

    @Query(nativeQuery = true,
            value = "select sum(t.price)\n" +
                    "from tickets t\n" +
                    "join trams tr on t.id_tram=tr.id\n" +
                    "where t.price>7 and tr.depo=:depo and t.day=:day")
    int sumTravelCardByDepo(LocalDate day, String depo);

//    @Query(nativeQuery = true,
//            value = "select sum(t.price)\n" +
//                    "from tickets t\n" +
//                    "join trams tm on t.id_tram=tm.id\n" +
//                    "join tracks tr on t.id_track=tr.id\n" +
//                    "where t.day=:day and tm.depo=:depo and tr.track=:track and t.price=7")
//    int sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);
}
