package com.example.fuelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleManagementService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}

