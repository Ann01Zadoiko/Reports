package com.example.demo.tram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TramRepository extends JpaRepository<Tram, Long> {

     Tram findByDepoAndNumberOfTram(String depo, String numberOfTram);

     Long findIdByDepoAndNumberOfTram(String depo, String numberOfTram);

     List<Tram> findByDepo(String depo);

}
