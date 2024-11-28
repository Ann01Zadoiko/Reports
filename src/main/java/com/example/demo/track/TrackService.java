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

    public void add(Track track){
        trackRepository.save(track);
    }

    public List<Track> getByDay(LocalDate day){
        return trackRepository.findByDay(day);
    }

    public Track getByDayAndIdTram(LocalDate day, Long id){
        return trackRepository.findByDayAndIdTram(day, id);
    }

    public List<String> getListTracks(LocalDate day, String depo){
        return trackRepository.listTracks(day, depo);
    }

    public void delete(Track track){
        trackRepository.delete(track);
    }

    public List<Track> getByDayAndTram(LocalDate day,Tram tram){
        return trackRepository.findByDayAndTram(day, tram);
    }

    public List<Track> getAll(){
        return trackRepository.findAll();
    }


}
