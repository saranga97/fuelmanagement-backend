package com.example.fuelmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String location;
    private String ownerName;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<FuelStock> fuelStocks;


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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<FuelStock> getFuelStocks() {
        return fuelStocks;
    }

    public void setFuelStocks(List<FuelStock> fuelStocks) {
        this.fuelStocks = fuelStocks;
    }
}
