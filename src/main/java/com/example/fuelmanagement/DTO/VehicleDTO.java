package com.example.fuelmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VehicleDTO {

    // Getters and Setters
    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Engine number is required")
    private String engineNumber;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Engine capacity is required")
    private int engineCapacity;

    @NotBlank(message = "Fuel type is required")
    private String fuelType;  // "Petrol" or "Diesel"

    @NotNull(message = "Manufactured year is required")
    private int manufacturedYear;

    @NotBlank(message = "Vehicle class is required")
    private String vehicleClass;  // Example: A, B, etc.

    private String ownershipId;  // This will be set from the JWT token

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setOwnershipId(String ownershipId) {
        this.ownershipId = ownershipId;
    }
}
