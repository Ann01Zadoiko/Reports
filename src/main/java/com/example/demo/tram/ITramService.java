package com.example.demo.tram;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ITramService {

    void add(Tram tram);

    boolean isExists(String depo, String numberOfTram);

    Tram getByDepoAndNumberOfTram(String depo, String numberOfTram);

    List<Tram> getAll();

    void deleteById(Long id);

    Tram getById(Long id);

    Set<Tram> getByDayAndPrice(LocalDate day);
}
