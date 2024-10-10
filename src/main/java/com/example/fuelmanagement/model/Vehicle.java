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


    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public String getConditionsAndNotes() {
        return conditionsAndNotes;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getOwnershipName() {
        return ownershipName;
    }

    public void setOwnershipName(String ownershipName) {
        this.ownershipName = ownershipName;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
