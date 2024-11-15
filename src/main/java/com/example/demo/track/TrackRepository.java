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

    @Query(nativeQuery = true, value = "select tr.* from tracks tr where tr.id_tram=:id and tr.day=:day")
    Track findByDayAndIdTram(LocalDate day, Long id);

    Track findByDayAndTram(LocalDate day, Tram tram);

    List<Track> findByTram(Tram tram);

    @Query(nativeQuery = true,
            value = "select tr.* \n" +
                    "from tracks tr\n" +
                    "join trams tm on tr.id_tram=tm.id\n" +
                    "where tr.day=:day and tm.depo=:depo")
    List<Track> findByDayAndDepo(LocalDate day, String depo);

    @Query(nativeQuery = true,
            value = "select distinct tr.first_part\n" +
                    "from tracks tr \n" +
                    "join trams tm on tr.id_tram=tm.id\n" +
                    "where tr.day=:day and tm.depo=:depo")
    List<String> listTracks(LocalDate day, String depo);
}
