package com.example.demo.track;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackService implements ITrackService{

    private final TrackRepository trackRepository;

    //add a new track
    @Override
    public void add(Track track){
        trackRepository.save(track);
    }

    //get a list of tracks by a day
    @Override
    public List<Track> getByDay(LocalDate day){
        return trackRepository.findByDay(day);
    }

    //get the track by a day and id tram
    @Override
    public Track getByDayAndIdTram(LocalDate day, Long id){
        return trackRepository.findByDayAndIdTram(day, id);
    }

    //get a list of tracks by a day and a depo
    @Override
    public List<String> getListTracks(LocalDate day, String depo){
        return trackRepository.listTracks(day, depo);
    }

    public List<Object[]> getDayAndDepo() {
        return trackRepository.findDistinctDayAndDepo();
    }

    public List<Track> findDuplicateTracksDetailed(){
        return trackRepository.findDuplicateTracksDetailed();
    }

    @Transactional
    public void deleteById(Long id){
        trackRepository.deleteById(id);
    }

}
