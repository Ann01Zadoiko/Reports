package com.example.demo.tram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TramService {

    private final TramRepository tramRepository;

    public void add(Tram tram){
        tramRepository.save(tram);
    }

    public void isExist(String depo, String numberOfTram){
    }

    public Tram getByDepoAndNumberOfTram(String depo, String numberOfTram){
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
}
