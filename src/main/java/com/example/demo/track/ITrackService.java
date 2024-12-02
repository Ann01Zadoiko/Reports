package com.example.demo.track;

import java.time.LocalDate;
import java.util.List;

public interface ITrackService {

    void add(Track track);

    List<Track> getByDay(LocalDate day);

    Track getByDayAndIdTram(LocalDate day, Long id);

    List<String> getListTracks(LocalDate day, String depo);
}
