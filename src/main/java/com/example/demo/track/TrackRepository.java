package com.example.demo.track;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {


    List<Track> findByDay(LocalDate day);

    Track findByTramAndDay(Tram tram, LocalDate day);

    @Query(nativeQuery = true, value = "select tr.* from tracks tr where tr.id_tram=:id and tr.day=:day")
    Track findByDayAndIdTram(LocalDate day, Long id);
}
