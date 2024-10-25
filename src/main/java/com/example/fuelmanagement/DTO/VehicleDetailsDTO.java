package com.example.fuelmanagement.DTO;

import com.example.fuelmanagement.model.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VehicleDetailsDTO {
    private String registrationNumber;
    private FuelType fuelType;
    private int allowedWeeklyCapacity;
    private double remainingQuota;
}
