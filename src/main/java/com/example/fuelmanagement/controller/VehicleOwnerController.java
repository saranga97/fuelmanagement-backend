package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.VehicleDTO;
import com.example.fuelmanagement.service.VehicleManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-owner")
public class VehicleOwnerController {

    @Autowired
    private VehicleManagementService vehicleManagementService;

    @PostMapping("/register")
    public ResponseEntity<VehicleDTO> registerVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createdVehicle = vehicleManagementService.registerVehicle(vehicleDTO);
        return ResponseEntity.ok(createdVehicle);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleManagementService.getAllVehicles());
    }
}
