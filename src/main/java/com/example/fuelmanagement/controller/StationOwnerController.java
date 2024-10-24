package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.model.FuelInventory;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.service.StationOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-owner")
public class StationOwnerController {

    private final StationOwnerService stationOwnerService;

    public StationOwnerController(StationOwnerService stationOwnerService) {
        this.stationOwnerService = stationOwnerService;
    }

    /**
     * Get all stations owned by the currently logged-in station owner.
     */
    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getOwnedStations(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<Station> stations = stationOwnerService.getStationsByOwnerUsername(userDetails.getUsername());
        return ResponseEntity.ok(stations);
    }

    /**
     * Get the fuel inventory details for a specific station owned by the logged-in owner.
     */
    @GetMapping("/stations/{stationId}/fuel-inventory")
    public ResponseEntity<FuelInventory> getFuelInventoryForStation(
            @PathVariable Long stationId,
            @AuthenticationPrincipal UserDetails userDetails) {

        FuelInventory fuelInventory = stationOwnerService.getFuelInventoryForStation(stationId, userDetails.getUsername());
        return ResponseEntity.ok(fuelInventory);
    }
}
