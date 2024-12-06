package com.example.demo.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    //find a list of track by a day
    List<Track> findByDay(LocalDate day);

    //find the track by a day and id track
    @Query(nativeQuery = true, value = "select tr.* from tracks tr where tr.id_tram=:id and tr.day=:day")
    Track findByDayAndIdTram(LocalDate day, Long id);

    //find a list of track by a day and a depo
    @Query(nativeQuery = true,
            value = "select distinct tr.first_part " +
                    "from tracks tr " +
                    "join trams tm on tr.id_tram=tm.id " +
                    "where tr.day=:day and tm.depo=:depo")
    List<String> listTracks(LocalDate day, String depo);

    @Query(value = "SELECT DISTINCT tr.day, tm.depo " +
            "FROM tracks tr " +
            "JOIN trams tm ON tr.id_tram = tm.id",
            nativeQuery = true)
    List<Object[]> findDistinctDayAndDepo();
}
