package com.example.demo.ticket.count;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CountTravelCardService implements ICountTravelCardService{

    private final CountTravelCardRepository repository;

    @Override
    public Integer countByTravelCardAndDay(LocalDate day, String travelCard) {
        Integer count = repository.countByTravelCardAndDay(day, travelCard);
        return count != null ? count : 0;
    }

    @Override
    public Integer countByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo) {
        Integer count = repository.countByTravelCardAndDayAndDepo(day, travelCard, depo);
        return count != null ? count : 0;
    }

    @Override
    public int countTravelCard(LocalDate day) {
        return repository.countTravelCard(day);
    }

    @Override
    public int countTravelCardDepo(LocalDate day, String depo) {
        return repository.countTravelCardDepo(day, depo);
    }
}
