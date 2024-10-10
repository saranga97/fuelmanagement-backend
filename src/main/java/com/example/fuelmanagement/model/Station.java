package com.example.fuelmanagement.model;

import jakarta.persistence.*;


@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String district;
    private String address;
    private String ownershipIdNumber;
    private String ownershipEmail;
    private double remainingFuelAmount;

    public Station() {
    }

    public Station(String stationName, String district, String address, String ownershipIdNumber, String ownershipEmail, double remainingFuelAmount) {
        this.stationName = stationName;
        this.district = district;
        this.address = address;
        this.ownershipIdNumber = ownershipIdNumber;
        this.ownershipEmail = ownershipEmail;
        this.remainingFuelAmount = remainingFuelAmount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnershipIdNumber() {
        return ownershipIdNumber;
    }

    public void setOwnershipIdNumber(String ownershipIdNumber) {
        this.ownershipIdNumber = ownershipIdNumber;
    }

    public String getOwnershipEmail() {
        return ownershipEmail;
    }

    public void setOwnershipEmail(String ownershipEmail) {
        this.ownershipEmail = ownershipEmail;
    }

    public double getRemainingFuelAmount() {
        return remainingFuelAmount;
    }

    public void setRemainingFuelAmount(double remainingFuelAmount) {
        this.remainingFuelAmount = remainingFuelAmount;
    }
}
