package com.example.demo.tram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TramService implements ITramService{

    private final TramRepository tramRepository;

    //add a new tram
    @Override
    public void add(Tram tram){
        tramRepository.save(tram);
    }

    //check a tram by depo and number
    @Override
    public boolean isExists(String depo, String numberOfTram){
        List<Tram> trams = tramRepository.findByDepo(depo);
        for (Tram tram:  trams){
            if (tram.getNumberOfTram().equals(numberOfTram)){
                return true;
            }
        }

        return false;
    }

    //get the tram by depo and number
    @Override
    public Tram getByDepoAndNumberOfTram(String depo, String numberOfTram){
        if (!isExists(depo, numberOfTram)){
            Tram tram = new Tram();
            tram.setNumberOfTram(numberOfTram);
            tram.setDepo(depo);
            tramRepository.save(tram);
        }

        return tramRepository.findByDepoAndNumberOfTram(depo, numberOfTram);
    }

    //get a list of trams
    @Override
    public List<Tram> getAll(){
        return tramRepository.findAll();
    }

    //delete a tram by id
    @Override
    public void deleteById(Long id){
        tramRepository.deleteById(id);
    }

    //get a tram by id
    @Override
    public Tram getById(Long id){
        if (tramRepository.findById(id).isEmpty() || tramRepository.findById(id).isPresent()){
            return  tramRepository.findById(id).get();
        }

        return null;
    }

    //get set of trams by a day and price is 15
    @Override
    public Set<Tram> getByDayAndPrice(LocalDate day){
        return tramRepository.findByDayAndPrice(day);
    }
}
