package com.example.demo.track;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByDay(LocalDate day);

    @Query(nativeQuery = true, value = "select tr.* from tracks tr where tr.id_tram=:id and tr.day=:day")
    Track findByDayAndIdTram(LocalDate day, Long id);


    @Query(nativeQuery = true,
            value = "select distinct tr.first_part " +
                    "from tracks tr " +
                    "join trams tm on tr.id_tram=tm.id " +
                    "where tr.day=:day and tm.depo=:depo")
    List<String> listTracks(LocalDate day, String depo);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "WITH CTE AS (" +
                    " SELECT id, ROW_NUMBER() OVER (PARTITION BY day, id_tram ORDER BY id) AS row_num" +
                    " FROM tracks)" +
                    " DELETE FROM tracks WHERE id IN (" +
                    " SELECT id FROM CTE WHERE row_num > 1)")
    void deleteDuplicates();

}
