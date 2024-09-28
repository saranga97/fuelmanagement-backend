package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String registrationNumber;
    @Getter
    private String ownerName;
    @Getter
    private String engineNumber;
    @Getter
    private String vehicleClass;
    @Getter
    private String conditionsAndNotes;
    @Getter
    private String make;
    @Getter
    private String model;
    private int yearOfManufacture;
    private String ownershipName;
    private boolean isMortgaged;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setConditionsAndNotes(String conditionsAndNotes) {
        this.conditionsAndNotes = conditionsAndNotes;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
// Getters and setters
}
