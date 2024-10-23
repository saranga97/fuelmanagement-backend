package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.model.StationOwner;
import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Get all registered station owners (Only accessible by admin)
    @GetMapping("/station-owners")
    public ResponseEntity<List<StationOwner>> getAllStationOwners(
            @AuthenticationPrincipal UserDetails userDetails) {
        validateAdminAccess(userDetails);
        List<StationOwner> stationOwners = adminService.getAllStationOwners();
        return ResponseEntity.ok(stationOwners);
    }

    // Get all registered vehicle owners (Only accessible by admin)
    @GetMapping("/vehicle-owners")
    public ResponseEntity<List<VehicleOwner>> getAllVehicleOwners(
            @AuthenticationPrincipal UserDetails userDetails) {
        validateAdminAccess(userDetails);
        List<VehicleOwner> vehicleOwners = adminService.getAllVehicleOwners();
        return ResponseEntity.ok(vehicleOwners);
    }

    // Get all registered vehicles (Only accessible by admin)
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(
            @AuthenticationPrincipal UserDetails userDetails) {
        validateAdminAccess(userDetails);
        List<Vehicle> vehicles = adminService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Endpoint to register a new station
    @PostMapping("/stations/register")
    public ResponseEntity<Station> registerStation(
            @RequestBody Station station,
            @AuthenticationPrincipal UserDetails userDetails) {

        validateAdminAccess(userDetails);
        Station registeredStation = adminService.registerStation(
                station.getName(), station.getAddress(), station.getStationOwner().getId());
        return ResponseEntity.ok(registeredStation);
    }


    // Validate that the user accessing these endpoints is an admin
    private void validateAdminAccess(UserDetails userDetails) {
        if (userDetails == null || !userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new SecurityException("Access denied. Only admins are authorized.");
        }
    }
}
