package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "domt_vehicles", uniqueConstraints = {@UniqueConstraint(columnNames = "registration_number")})
public class DOMTVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String engineNumber;
    private String manufacturer;
    private String model;
    private int engineCapacity;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    private int manufacturedYear;

    @Column(nullable = false)
    private String vehicleClass;  // Ensure this field exists

    private String ownershipId;

    // Getters and Setters

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
}
