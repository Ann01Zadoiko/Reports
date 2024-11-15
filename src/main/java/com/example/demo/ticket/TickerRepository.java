package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
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


    @Query(nativeQuery = true,
            value = "select sum(t.price)\n" +
                    "from reports.tickets t\n" +
                    "join reports.tracks tr on t.id_track=tr.id\n" +
                    "where tr.time is null and t.price=7 and t.day=:day and " +
                    "tr.first_part=:firstPart")
    int sumTicketsFullDay(LocalDate day, String firstPart);

    @Query(nativeQuery = true,
            value = "\n" +
                    "select sum(t.price)\n" +
                    "from reports.tickets t\n" +
                    "join reports.tracks tr on t.id_track=tr.id\n" +
                    "where tr.time >:time and tr.time>t.time and t.price=7 and " +
                    "t.day=:day  and tr.first_part=:firstPart")
    int sumTicketsFirstPart(LocalDate day, LocalTime time, String firstPart);

    @Query(nativeQuery = true,
            value = "\n" +
                    "select sum(t.price)\n" +
                    "from reports.tickets t\n" +
                    "join reports.tracks tr on t.id_track=tr.id\n" +
                    "where tr.time<:time and tr.time<t.time and t.price=7 and t.day=:day " +
                    "and tr.second_part=:secondPart")
    int sumTicketsSecondPart(LocalDate day, LocalTime time, String secondPart);

    @Query(nativeQuery = true,
            value = "select t.* from tickets t where t.day =:day and t.price>7")
    List<Ticket> findTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) \n" +
                    "from tickets t\n" +
                    "join tracks tr on t.id_track = tr.id\n" +
                    "join trams tm on t.id_tram=tm.id\n" +
                    "where depo=:depo \n" +
                    "and tr.day=t.day and tr.day=:day and t.price=7 and\n" +
                    "((tr.time is null and tr.first_part=:track) or\n" +
                    "(tr.time>t.time and tr.first_part=:track ) or\n" +
                    "(tr.time<t.time and tr.second_part=:track) )")
    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);
}
