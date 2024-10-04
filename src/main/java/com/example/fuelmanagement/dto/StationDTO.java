package com.example.fuelmanagement.dto;

import jakarta.validation.constraints.NotBlank;


public class StationDTO {
    private Long id;

    @NotBlank(message = "Station name is required")
    private String stationName;

    @NotBlank(message = "Station location is required")
    private String location;

    // Add this to include owner data in the DTO
    private String ownerUsername;
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Station name is required") String getStationName() {
        return stationName;
    }

    public void setStationName(@NotBlank(message = "Station name is required") String stationName) {
        this.stationName = stationName;
    }

    public @NotBlank(message = "Station location is required") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Station location is required") String location) {
        this.location = location;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
