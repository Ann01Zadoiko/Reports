package com.example.demo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTicketService {

    private final FileTicketRepository repository;

    public boolean isExists(String name){
        return repository.existsByName(name);
    }

    public void add(FileTicket fileTicket){
        repository.save(fileTicket);
    }
}
