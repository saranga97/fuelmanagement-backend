package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String registrationNumber;
    private String engineNumber;
    private String manufacturer;
    private String model;
    private int engineCapacity;
    private String fuelType;
    private int manufacturedYear;
    private String vehicleClass;
    private String ownershipId;
}
