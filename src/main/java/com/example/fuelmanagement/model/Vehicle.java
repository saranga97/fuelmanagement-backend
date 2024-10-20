package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;
    private String engineNumber;
    private String manufacturer;
    private String model;
    private int engineCapacity;
    private String fuelType;
    private int manufacturedYear;
    private int remainingQuota;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @ManyToOne
    @JoinColumn(name = "vehicle_class_id")
    private VehicleClass vehicleClass;
}
