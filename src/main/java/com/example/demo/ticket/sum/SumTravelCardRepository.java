package com.example.demo.ticket.sum;

import com.example.demo.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SumTravelCardRepository extends JpaRepository<Ticket, Long> {

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t where t.day = :day and t.price>15")
    int sumTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day = :day and t.price>15 and tr.depo=:depo")
    Integer sumTravelCardDepo(LocalDate day, String depo);

//    @Query(nativeQuery = true,
//            value = "select sum(t.price) from tickets t " +
//                    "join trams tr on t.id_tram=tr.id " +
//                    "where t.price>15 and tr.depo=:depo and t.day=:day")
//    int sumTravelCardByDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "where t.day=:day and t.travel_card=:travelCard")
    Integer sumByTravelCardAndDay(LocalDate day, String travelCard);

    @Query(nativeQuery = true,
            value = "select sum(t.price) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.travel_card=:travelCard and tr.depo=:depo")
    Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);
}
