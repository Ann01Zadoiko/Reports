package com.example.demo.ticket.sum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SumTravelCardService implements ISumTravelCardService{

    private final SumTravelCardRepository repository;

    @Override
    public int sumTravelCard(LocalDate day) {
        return repository.sumTravelCard(day);
    }

    @Override
    public int sumTravelCardDepo(LocalDate day, String depo) {
        return repository.sumTravelCardDepo(day, depo);
    }

    @Override
    public int sumTravelCardByDepo(LocalDate day, String depo) {
        return repository.sumTravelCardByDepo(day, depo);
    }

    @Override
    public Integer sumByTravelCardAndDay(LocalDate day, String travelCard) {
        Integer sum = repository.sumByTravelCardAndDay(day, travelCard);
        return sum != null ? sum : 0;
    }

    @Override
    public Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo) {
        Integer sum = repository.sumByTravelCardAndDayAndDepo(day, travelCard, depo);
        return sum != null ? sum : 0;
    }
}
