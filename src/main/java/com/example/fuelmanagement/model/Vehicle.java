package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;
    private String ownerName;
    private String engineNumber;
    private String vehicleClass;
    private String conditionsAndNotes;
    private String make;
    private String model;
    private int yearOfManufacture;
    private String ownershipName;
    private boolean isMortgaged;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}
