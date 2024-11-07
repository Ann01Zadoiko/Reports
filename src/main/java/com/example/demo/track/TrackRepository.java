package com.example.demo.track;

import com.example.demo.tram.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByTrack(String track);

    List<Track> findByDay(LocalDate day);

    Track findByTramAndDay(Tram tram, LocalDate day);
}
