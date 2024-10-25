package com.example.fuelmanagement.DTO;

import com.example.fuelmanagement.model.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpFuelRequest {
    private String registrationNumber;
    private double litersPumped;
    private FuelType fuelType;
}
