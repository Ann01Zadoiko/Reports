package com.example.demo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTicketService implements IFileTicketService{

    private final FileTicketRepository repository;

    //check a file by name
    @Override
    public boolean isExists(String name){
        return repository.existsByName(name);
    }

    //add a new file
    @Override
    public void add(FileTicket fileTicket){
        repository.save(fileTicket);
    }
}
