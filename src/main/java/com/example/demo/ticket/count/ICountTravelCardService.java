package com.example.demo.ticket.count;

import java.time.LocalDate;

public interface ICountTravelCardService {

    Integer countByTravelCardAndDay(LocalDate day, String travelCard);

    Integer countByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);

    int countTravelCard(LocalDate day);

    int countTravelCardDepo(LocalDate day, String depo);
}
