package com.example.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTicketRepository extends JpaRepository<FileTicket, Long> {

    //check a file by name
    boolean existsByName(String name);
}
