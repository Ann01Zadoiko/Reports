package com.example.demo.ticket.count;

import com.example.demo.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CountTravelCardRepository extends JpaRepository<Ticket, Long> {

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "where t.day=:day and t.travel_card=:travelCard")
    Integer countByTravelCardAndDay(LocalDate day, String travelCard);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day=:day and t.travel_card=:travelCard and tr.depo=:depo")
    Integer countByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t where t.day = :day and t.price>15")
    int countTravelCard(LocalDate day);

    @Query(nativeQuery = true,
            value = "select count(*) from tickets t " +
                    "join trams tr on t.id_tram=tr.id " +
                    "where t.day = :day and t.price>15 and tr.depo=:depo")
    int countTravelCardDepo(LocalDate day, String depo);
}
