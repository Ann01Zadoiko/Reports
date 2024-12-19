package com.example.demo.ticket.sum;

import java.time.LocalDate;

public interface ISumTravelCardService {

    Integer sumTravelCard(LocalDate day);

    Integer sumTravelCardDepo(LocalDate day, String depo);

    Integer sumByTravelCardAndDay(LocalDate day, String travelCard);

    Integer sumByTravelCardAndDayAndDepo(LocalDate day, String travelCard, String depo);
}
