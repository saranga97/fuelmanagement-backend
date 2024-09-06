package com.example.fuelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fuelmanagement.service.VehicleManagementService;
import com.example.fuelmanagement.model.Vehicle;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleOwnerController {

    @Autowired
    private VehicleManagementService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            vehicle.setModelName(vehicleDetails.getModelName());
            vehicle.setNumberPlateID(vehicleDetails.getNumberPlateID());
            return vehicleService.saveVehicle(vehicle);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}

