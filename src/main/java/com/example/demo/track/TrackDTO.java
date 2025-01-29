package com.example.demo.track;

import com.example.demo.tram.Tram;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrackDTO {

    private Long tramId;
    private String track1;
    private LocalTime time;  // Необязательное поле
    private String track2;
    private LocalDate day;
    private Tram tram;

    public Tram getTram() {
        return tram;
    }

    public void setTram(Tram tram) {
        this.tram = tram;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }


    // Геттеры и сеттеры
    public Long getTramId() { return tramId; }
    public void setTramId(Long tramId) { this.tramId = tramId; }

    public String getTrack1() { return track1; }
    public void setTrack1(String track1) { this.track1 = track1; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getTrack2() { return track2; }
    public void setTrack2(String track2) { this.track2 = track2; }
}
