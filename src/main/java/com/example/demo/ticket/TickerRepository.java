package com.example.demo.ticket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByDay(LocalDate day);

    @Query("SELECT DISTINCT t.day FROM Ticket t")
    List<LocalDate> findDistinctDays();

    @Query(nativeQuery = true,
            value = "select t.* from tickets t where t.day =:day and t.price>15")
    List<Ticket> findTravelCard(LocalDate day);

}
