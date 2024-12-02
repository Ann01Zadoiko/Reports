package com.example.demo.ticket.sum;

import java.time.LocalDate;

public interface ISumTravelCardService {

    int sumTravelCard(LocalDate day);

    int sumTravelCardDepo(LocalDate day, String depo);

    int sumTravelCardByDepo(LocalDate day, String depo);

    Integer sumByTravelCardAndDay(LocalDate day, String travelCard);

    Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);
}
