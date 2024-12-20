package com.example.demo.ticket.sum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SumTravelCardService implements ISumTravelCardService{

    private final SumTravelCardRepository repository;

    //sum of travel cards by day
    @Override
    public Integer sumTravelCard(LocalDate day) {
        Integer sum = repository.sumTravelCard(day);
        return sum != null ? sum : 0;
    }

    //sum of travel cards by day and depo
    @Override
    public Integer sumTravelCardDepo(LocalDate day, String depo) {
        Integer sum = repository.sumTravelCardDepo(day, depo);
        return sum != null ? sum : 0;
    }

    //sum by type of travel card and day
    @Override
    public Integer sumByTravelCardAndDay(LocalDate day, String travelCard) {
        Integer sum = repository.sumByTravelCardAndDay(day, travelCard);
        return sum != null ? sum : 0;
    }

    //sum by day, dapo and type of travel card
    @Override
    public Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo) {
        Integer sum = repository.sumByTravelCardAndDayAndDepo(day, travelCard, depo);
        return sum != null ? sum : 0;
    }

    @Override
    public Integer sumByTravelCardMonthDepo(String month, String year, String depo, String travelCard) {
        Integer sum = repository.sumByTravelCardMonthDepo(month, year, depo, travelCard);
        return sum != null ? sum : 0;
    }

    @Override
    public Integer sumByMonthDepo(String month, String year, String depo) {
        Integer sum = repository.sumByMonthDepo(month, year, depo);
        return sum != null ? sum : 0;
    }
}
