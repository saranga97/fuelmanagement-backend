package com.example.fuelmanagement.dto;

import jakarta.validation.constraints.NotBlank;


public class StationDTO {
    private Long id;

    @NotBlank(message = "Station name is required")
    private String stationName;

    @NotBlank(message = "Station location is required")
    private String location;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    // Getters and Setters
}
