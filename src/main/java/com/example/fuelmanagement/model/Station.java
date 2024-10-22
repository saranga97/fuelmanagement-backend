package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    private String stationId;

    private String address;

    @Column(nullable = false)
    private String ownershipId;

    @Column(nullable = false)
    private String ownershipEmail;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FuelInventory> fuelInventory;

    // Getters and setters
}
