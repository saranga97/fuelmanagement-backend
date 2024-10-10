package com.example.fuelmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    private String registrationNumber;

    private String engineNumber;
    private String manufacturer;
    private String model;
    private int engineCapacity;
    private String fuelType;
    private int manufacturedYear;
    private String vehicleClass;
    private String ownershipIdNumber;
    private double remainingQuota;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber, String engineNumber, String manufacturer, String model, int engineCapacity, String fuelType, int manufacturedYear, String vehicleClass, String ownershipIdNumber, double remainingQuota) {
        this.registrationNumber = registrationNumber;
        this.engineNumber = engineNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.manufacturedYear = manufacturedYear;
        this.vehicleClass = vehicleClass;
        this.ownershipIdNumber = ownershipIdNumber;
        this.remainingQuota = remainingQuota;
    }

    // Getters and Setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getOwnershipIdNumber() {
        return ownershipIdNumber;
    }

    public void setOwnershipIdNumber(String ownershipIdNumber) {
        this.ownershipIdNumber = ownershipIdNumber;
    }

    public double getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(double remainingQuota) {
        this.remainingQuota = remainingQuota;
    }
}
