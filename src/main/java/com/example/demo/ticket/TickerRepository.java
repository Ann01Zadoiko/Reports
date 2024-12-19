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

//    @Query(nativeQuery = true,
//            value = "SELECT distinct\n" +
//                    " t.day\n" +
//                    "FROM tickets t \n" +
//                    "where EXTRACT(YEAR FROM t.day)=:year and EXTRACT(MONTH FROM t.day)=:month")
    @Query("SELECT DISTINCT t.day " +
        "FROM Ticket t " +
        "WHERE FUNCTION('YEAR', t.day) = :year " +
        "AND FUNCTION('MONTH', t.day) = :month")
    List<LocalDate> findDaysOfMonth(String year, String month);
}
