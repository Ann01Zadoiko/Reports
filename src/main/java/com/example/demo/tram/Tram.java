package com.example.demo.tram;

import com.example.demo.ticket.Ticket;
import com.example.demo.track.Track;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "trams")
@AllArgsConstructor
@NoArgsConstructor
public class Tram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depo")
    private String depo;

    @Column(name = "number_of_tram")
    private int numberOfTram;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tram", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tram", cascade = CascadeType.ALL)
    private List<Track> tracks;
}
