package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.FuelInventoryUpdateDTO;
import com.example.fuelmanagement.DTO.StationResponseDTO;
import com.example.fuelmanagement.DTO.StationWithOwnerDTO;
import com.example.fuelmanagement.model.*;
import com.example.fuelmanagement.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.fuelmanagement.model.FuelInventory;
import java.util.ArrayList;
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

//    @GetMapping("/stations")
//    public ResponseEntity<List<Station>> getAllStationsWithOwners(
//            @AuthenticationPrincipal UserDetails userDetails) {
//
//        validateAdminAccess(userDetails);  // Ensure only admin can access this
//
//        List<Station> stations = adminService.getAllStations();
//        return ResponseEntity.ok(stations);
//    }

//    @GetMapping("/stations")
//    public ResponseEntity<List<StationWithOwnerDTO>> getAllStationsWithOwners(
//            @AuthenticationPrincipal UserDetails userDetails) {
//
//        validateAdminAccess(userDetails);
//
//        List<StationWithOwnerDTO> stations = adminService.getAllStationsWithOwners();
//        return ResponseEntity.ok(stations);
//    }

    @GetMapping("/stations")
    public ResponseEntity<List<StationResponseDTO>> getAllStationsWithFuelQuota(
            @AuthenticationPrincipal UserDetails userDetails) {

        validateAdminAccess(userDetails);

        List<Station> stations = adminService.getAllStations();
        List<StationResponseDTO> response = new ArrayList<>();

        for (Station station : stations) {
            FuelInventory inventory = adminService.getFuelInventoryByStation(station);
            response.add(new StationResponseDTO(
                    station.getName(),
                    station.getAddress(),
                    station.getStationOwner().getFullName(),
                    station.getStationOwner().getEmail(),
                    inventory.getDieselQuota(),
                    inventory.getSuperDieselQuota(),
                    inventory.getPetrol92Quota(),
                    inventory.getPetrol95Quota()
            ));
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/stations/update-fuel-quota")
    public ResponseEntity<String> updateFuelQuota(
            @RequestBody FuelInventoryUpdateDTO fuelInventoryUpdateDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        validateAdminAccess(userDetails);

        adminService.updateFuelQuota(fuelInventoryUpdateDTO);

        return ResponseEntity.ok("Fuel quotas updated successfully for station ID: " + fuelInventoryUpdateDTO.getStationId());
    }


    // Validate that the user accessing these endpoints is an admin
    private void validateAdminAccess(UserDetails userDetails) {
        if (userDetails == null || !userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new SecurityException("Access denied. Only admins are authorized.");
        }
    }
}
