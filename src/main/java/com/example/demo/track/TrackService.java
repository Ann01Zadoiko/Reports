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

    public void deleteAll(){
        trackRepository.deleteAll();
    }

    public List<Track> getByDay(LocalDate day){
        return trackRepository.findByDay(day);
    }

    public Track getByDayAndIdTram(LocalDate day, Long id){
        return trackRepository.findByDayAndIdTram(day, id);
    }

    public List<Track> getByTram(Tram tram){
        return trackRepository.findByTram(tram);
    }

    public boolean isExists(Track track){
        List<Track> tracks = getByTram(track.getTram());

        for (Track track1: tracks){
            if (track.getDay().equals(track1.getDay())){
                return true;
            }
        }

        return false;
    }

    public List<Track> getByDayAndDepo(LocalDate day, String depo){
        return trackRepository.findByDayAndDepo(day, depo);
    }

    public List<String> getListTracks(LocalDate day, String depo){
        return trackRepository.listTracks(day, depo);
    }

}
