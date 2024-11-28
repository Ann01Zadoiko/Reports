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

    List<Ticket> findByDay(LocalDate day);

    @Query("SELECT DISTINCT t.day FROM Ticket t")
    List<LocalDate> findDistinctDays();

    List<Ticket> findByDayAndTram(LocalDate day, Tram tram);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day =:day and t.price=7")
    int sumTickets(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day = :day and t.price>7")
    int sumTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day = :day and t.price>7 and tr.depo=:depo")
    int sumTravelCardDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t where t.day = :day and t.price>7")
    int countTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day = :day and t.price>7 and tr.depo=:depo")
    int countTravelCardDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
            "join trams tr on t.id_tram = tr.id " +
            "where t.day = :day and t.price=7 and tr.depo = :depo")
    int sumTicketsByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.price=7 and tr.depo=:depo " +
                    "and tr.number_of_tram=:numberOfTram")
    int sumTicketsByTram(LocalDate day, String depo, @Param("numberOfTram") String numberOfTram);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.price>7 and tr.depo=:depo and t.day=:day")
    int sumTravelCardByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select t.* from tickets t where t.day =:day and t.price>7")
    List<Ticket> findTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t join tracks tr on t.id_track = tr.id " +
                    "join trams tm on t.id_tram=tm.id where depo=:depo " +
                    "and tr.day=t.day and tr.day=:day and t.price=7 and " +
                    "((tr.time is null and tr.first_part=:track) or " +
                    "(tr.time>t.time and tr.first_part=:track ) or " +
                    "(tr.time<t.time and tr.second_part=:track) )")
    Integer sumTicketsByDepoAndTrack(LocalDate day, String depo, String track);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "where t.day=:day and t.travel_card=:travelCard")
    Integer sumByTravelCardAndDay(LocalDate day, String travelCard);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "where t.day=:day and t.travel_card=:travelCard")
    Integer countByTravelCardAndDay(LocalDate day, String travelCard);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.travel_card=:travelCard and tr.depo=:depo")
    Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.travel_card=:travelCard and tr.depo=:depo")
    Integer countByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);
}
