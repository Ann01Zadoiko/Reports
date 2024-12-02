package com.example.demo.tram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TramRepository extends JpaRepository<Tram, Long> {

     //find the tram by depo and number
     Tram findByDepoAndNumberOfTram(String depo, String numberOfTram);

     //find a list of trams by depo
     List<Tram> findByDepo(String depo);

     //find a set of trams by a day and price is 15
     @Query(nativeQuery = true,
             value = "select tr.* " +
                     "from trams tr " +
                     "join tickets t on tr.id=t.id_tram " +
                     "where t.day=:day and t.price=15")
     Set<Tram> findByDayAndPrice(LocalDate day);

}
