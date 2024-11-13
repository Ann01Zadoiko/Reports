package com.example.demo.ticket;

import com.example.demo.track.Track;
import com.example.demo.tram.Tram;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private LocalDate day;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tram")
    private Tram tram;

    @Column(name = "price")
    private Integer price;

    @Column(name = "time")
    private LocalTime time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_track")
    private Track track;
}
