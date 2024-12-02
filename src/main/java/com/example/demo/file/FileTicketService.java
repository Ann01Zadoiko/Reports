package com.example.demo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTicketService implements IFileTicketService{

    private final FileTicketRepository repository;

    @Override
    public boolean isExists(String name){
        return repository.existsByName(name);
    }

    @Override
    public void add(FileTicket fileTicket){
        repository.save(fileTicket);
    }
}
