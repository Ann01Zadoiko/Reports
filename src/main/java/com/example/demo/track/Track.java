package com.example.demo.track;

import com.example.demo.ticket.Ticket;
import com.example.demo.tram.Tram;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tracks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "first_part")
    private String firstPart;

    @Column(name = "second_part")
    private String secondPart;

    @Column(name = "time")
    private LocalTime time;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tram")
    private Tram tram;

}
