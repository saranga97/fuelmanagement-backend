package com.example.fuelmanagement.DTO;

import com.example.fuelmanagement.model.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpFuelRequest {
    private Long vehicleId;
    private double litersPumped;
    private FuelType fuelType;
}
