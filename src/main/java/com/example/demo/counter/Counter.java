package com.example.demo.counter;

import com.example.demo.tram.Tram;

import java.time.LocalDate;
import java.util.Map;

public interface Counter {

    int countByDayGeneral(String dayString);

    Map<Tram, Integer> countMapOfTram(LocalDate day, int amount);
}
