package com.example.demo.config;

import com.example.demo.ticket.TickerRepository;
import com.example.demo.ticket.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final TickerRepository repository;

    public AppConfig(TickerRepository repository) {
        this.repository = repository;
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService(repository);
    }
}
