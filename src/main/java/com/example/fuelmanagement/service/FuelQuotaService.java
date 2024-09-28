package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.FuelQuotaDTO;

import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelQuotaService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public FuelQuotaDTO getFuelQuotaByVehicleId(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleId));

        // Assuming FuelQuotaDTO contains the fuel quota details.
        return new FuelQuotaDTO(vehicle.getId(), vehicle.getRegistrationNumber(), /* remaining quota */ 100);
    }

    public void updateFuelQuota(Long vehicleId, int litres) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleId));

        // Logic to update the fuel quota
    }
}
