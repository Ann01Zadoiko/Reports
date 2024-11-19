package com.example.demo.tram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TramService {

    private final TramRepository tramRepository;

    public void add(Tram tram){
        tramRepository.save(tram);
    }

    public boolean isExists(String depo, String numberOfTram){

        List<Tram> trams = tramRepository.findByDepo(depo);
        for (Tram tram:  trams){
            if (tram.getNumberOfTram().equals(numberOfTram)){
                return true;
            }
        }

        return false;
    }

    public Tram getByDepoAndNumberOfTram(String depo, String numberOfTram){

        if (!isExists(depo, numberOfTram)){
            Tram tram = new Tram();
            tram.setNumberOfTram(numberOfTram);
            tram.setDepo(depo);
            tramRepository.save(tram);
        }

        return tramRepository.findByDepoAndNumberOfTram(depo, numberOfTram);
    }

    public List<Tram> getAll(){
        return tramRepository.findAll();
    }

    public void deleteById(Long id){
        tramRepository.deleteById(id);
    }

    public Tram getById(Long id){
        return tramRepository.findById(id).get();
    }

    public Set<Tram> getByDayAndPrice(LocalDate day){
        return tramRepository.findByDayAndPrice(day);
    }
}
