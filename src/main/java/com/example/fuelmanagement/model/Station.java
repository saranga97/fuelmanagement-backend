package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;  // Ensure this matches with the @JoinColumn

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "station_owner_id", nullable = false)
    private StationOwner stationOwner;

    public Station() {
    }

    public Station(String name, String address, StationOwner stationOwner) {
        this.name = name;
        this.address = address;
        this.stationOwner = stationOwner;
    }
}
