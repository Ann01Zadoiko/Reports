package com.example.demo.ticket;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTramAndDay(Tram tram, LocalDate day);

    List<Ticket> findByDay(LocalDate day);

    List<Ticket> findByTram(Tram tram);

    @Query("SELECT DISTINCT t.day FROM Ticket t")
    List<LocalDate> findDistinctDays();
}
