package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.VehicleDTO;
import com.example.fuelmanagement.DTO.VehicleOwnerResponseDTO;
import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Endpoint to register a new vehicle
    @PostMapping("/register")
    public ResponseEntity<?> registerVehicle(@RequestBody VehicleDTO vehicleDTO,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        // Fetch the vehicle owner's details to retrieve the ID card number
        VehicleOwnerResponseDTO ownerDetails = vehicleService.getLoggedInVehicleOwnerDetails(userDetails.getUsername());

        vehicleDTO.setOwnershipId(ownerDetails.getIdCardNumber());  // Set ownership ID to the ID card number
        Vehicle registeredVehicle = vehicleService.registerVehicle(vehicleDTO);
        return ResponseEntity.ok(registeredVehicle);
    }


    // Endpoint to get all vehicles owned by the logged-in user
    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehiclesByOwner(@AuthenticationPrincipal UserDetails userDetails) {
        // Use the ID card number instead of the username to fetch vehicles
        String idCardNumber = vehicleService.getLoggedInVehicleOwnerDetails(userDetails.getUsername()).getIdCardNumber();
        List<Vehicle> vehicles = vehicleService.getVehiclesByOwner(idCardNumber);
        return ResponseEntity.ok(vehicles);
    }


    // New endpoint to fetch logged-in vehicle owner details along with their vehicles
    @GetMapping("/owner-details")
    public ResponseEntity<VehicleOwnerResponseDTO> getLoggedInVehicleOwnerDetails(
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        VehicleOwnerResponseDTO responseDTO = vehicleService.getLoggedInVehicleOwnerDetails(username);
        return ResponseEntity.ok(responseDTO);
    }
}
