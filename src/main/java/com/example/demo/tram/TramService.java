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

    public boolean isExist(String depo, String numberOfTram){

        List<Tram> trams = tramRepository.findByDepo(depo);
        for (Tram tram:  trams){
            if (tram.getNumberOfTram().equals(numberOfTram)){
                return true;
            }
        }

        return false;
    }

    public Tram getByDepoAndNumberOfTram(String depo, String numberOfTram){

        if (!isExist(depo, numberOfTram)){
            Tram tram = new Tram();
            tram.setNumberOfTram(numberOfTram);
            tram.setDepo(depo);
            tramRepository.save(tram);
        }

        return tramRepository.findByDepoAndNumberOfTram(depo, numberOfTram);
    }

    public Long getIdByDepoAndNumberOfTram(String depo, String numberOfTram){
        return tramRepository.findIdByDepoAndNumberOfTram(depo, numberOfTram);
    }

    public List<Tram> getByDepo(String depo){
        return tramRepository.findByDepo(depo);
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

    public Set<Tram> getByDay(LocalDate day){
        return tramRepository.findByDay(day);
    }
}
