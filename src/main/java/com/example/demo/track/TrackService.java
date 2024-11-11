package com.example.demo.track;

import com.example.demo.tram.Tram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackService {

    private final TrackRepository trackRepository;

    public void add(Tram tram, String track, LocalDate day){
        Track track1 = new Track();
        track1.setTrack(track);
        track1.setDay(day);
        track1.setTram(tram);

        trackRepository.save(track1);
    }

    public void deleteAll(){
        trackRepository.deleteAll();
    }

    public List<Track> getByDay(LocalDate day){
        return trackRepository.findByDay(day);
    }

    public Track getByDayAndIdTram(LocalDate day, Long id){
        return trackRepository.findByDayAndIdTram(day, id);
    }
}
