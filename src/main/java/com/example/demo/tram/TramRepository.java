package com.example.demo.tram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TramRepository extends JpaRepository<Tram, Long> {

     Tram findByDepoAndNumberOfTram(String depo, String numberOfTram);

     List<Tram> findByDepo(String depo);

     @Query(nativeQuery = true,
             value = "select tr.* from trams tr join tickets t on tr.id=t.id_tram where t.day = :day")
     Set<Tram> findByDay(LocalDate day);

     @Query(nativeQuery = true,
             value = "select tr.*\n" +
                     "from trams tr \n" +
                     "join tickets t on tr.id=t.id_tram\n" +
                     "where t.day=:day and t.price=7;")
     Set<Tram> findByDayAndPrice(LocalDate day);

}
