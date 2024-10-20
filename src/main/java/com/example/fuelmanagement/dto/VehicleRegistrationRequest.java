package com.example.fuelmanagement.dto;

import lombok.Data;

@Data
public class VehicleRegistrationRequest {
    private String registrationNumber;
    private String engineNumber;
    private String manufacturer;
    private String model;
    private int engineCapacity;
    private String fuelType;
    private int manufacturedYear;
    private Long vehicleClassId;
}
