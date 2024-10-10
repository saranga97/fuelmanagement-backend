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

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<FuelStock> fuelStocks;

    //ManyToOne mapping to User (the owner of the station)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;


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

    public List<FuelStock> getFuelStocks() {
        return fuelStocks;
    }

    public void setFuelStocks(List<FuelStock> fuelStocks) {
        this.fuelStocks = fuelStocks;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
