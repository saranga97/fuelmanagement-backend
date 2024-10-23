package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_inventory")
public class FuelInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private int remainingQuota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", referencedColumnName = "stationId")
    private Station station;

    // Getters and setters
}
