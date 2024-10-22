package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_quota")
public class VehicleQuota {

    @Id
    @Column(name = "vehicle_class")
    private String vehicleClass;

    private int weeklyQuota;

    // Getters and setters
}
