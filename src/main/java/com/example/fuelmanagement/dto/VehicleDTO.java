package com.example.fuelmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class VehicleDTO {
    private Long id;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "Engine number is required")
    private String engineNumber;

    private String vehicleClass;
    private String conditionsAndNotes;
    private String make;
    private String model;
    private int yearOfManufacture;
    private String ownershipName;
    private boolean isMortgaged;

    // Getters and Setters
}
