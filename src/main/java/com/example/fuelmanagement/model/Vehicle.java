package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NumberPlateID;
    private String modelName;

    @Enumerated(EnumType.STRING)
    private FUEL_TYPE fuel_type;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlateID() {
        return NumberPlateID;
    }

    public void setNumberPlateID(String numberPlateID) {
        NumberPlateID = numberPlateID;
    }

    public FUEL_TYPE getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(FUEL_TYPE fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}

