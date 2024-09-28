package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "fuel_stock")
public class FuelStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FUEL_TYPE fuelType;  // petrol, diesel...
    private Double capacity;  // Capacity for this fuel type
    private Double availableStock;  // stock for this fuel type

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FUEL_TYPE getFuelType() {
        return fuelType;
    }

    public void setFuelType(FUEL_TYPE fuelType) {
        this.fuelType = fuelType;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Double availableStock) {
        this.availableStock = availableStock;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}

