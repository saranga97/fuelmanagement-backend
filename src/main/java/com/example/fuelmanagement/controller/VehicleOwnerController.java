package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.VehicleOwnerDTO;
import com.example.fuelmanagement.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle-owner")
public class VehicleOwnerController {

    private final VehicleService vehicleService;

    public VehicleOwnerController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Endpoint to update the logged-in vehicle owner's details
    @PutMapping("/update")
    public ResponseEntity<?> updateVehicleOwnerDetails(
            @RequestBody VehicleOwnerDTO ownerDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        vehicleService.updateVehicleOwner(ownerDTO, userDetails.getUsername());
        return ResponseEntity.ok("Vehicle owner details updated successfully.");
    }
}
