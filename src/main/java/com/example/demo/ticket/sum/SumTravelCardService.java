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
    public int sumTravelCard(LocalDate day) {
        return repository.sumTravelCard(day);
    }

    //sum of travel cards by day and depo
    @Override
    public int sumTravelCardDepo(LocalDate day, String depo) {
        return repository.sumTravelCardDepo(day, depo);
    }

//    @Override
//    public int sumTravelCardByDepo(LocalDate day, String depo) {
//        return repository.sumTravelCardByDepo(day, depo);
//    }

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
}
