package com.example.demo.ticket;


import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByDay(LocalDate day);

    @Query("SELECT DISTINCT t.day FROM Ticket t")
    List<LocalDate> findDistinctDays();

    @Query("SELECT DISTINCT t.day " +
        "FROM Ticket t " +
        "WHERE FUNCTION('YEAR', t.day) = :year " +
        "AND FUNCTION('MONTH', t.day) = :month")
    List<LocalDate> findDaysOfMonth(String year, String month);

    @Query("SELECT DISTINCT tm " +
            "FROM Ticket t " +
            "JOIN t.tram tm " +
            "WHERE tm.depo = :depo " +
            "  AND t.day = :day " +
            "  AND t.price = 15 " +
            "  AND t.track IS NULL")
    List<Tram> findListOfTramWithoutTrack(LocalDate day, String depo);

//    @Query(nativeQuery = true,
//            value = "select distinct tr.id, tr.number_of_tram, tr.depo  " +
//                    "from tickets t " +
//                    "join trams tr on t.id_tram=tr.id " +
//                    "where t.price=15 and tr.depo=:depo and t.day=:day")
    @Query("SELECT DISTINCT t.tram FROM Ticket t " +
        "WHERE t.price = 15 AND t.tram.depo = :depo AND t.day = :day")
    List<Tram> findTramsByDayAndDepo(@Param("day") LocalDate day,@Param("depo") String depo);
}
