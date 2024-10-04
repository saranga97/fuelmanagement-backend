package com.example.fuelmanagement.dto;


import com.example.fuelmanagement.model.FUEL_TYPE;

public class FuelStockDTO {
    private Long id;
    private FUEL_TYPE fuelType;
    private Double capacity;
    private Double availableStock;
    private Long stationId;  // To associate with a station

    // Getters and Setters
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

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
}

