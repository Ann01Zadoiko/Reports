package com.example.demo.file;

public interface IFileTicketService {

    boolean isExists(String name);

    void add(FileTicket fileTicket);
}
