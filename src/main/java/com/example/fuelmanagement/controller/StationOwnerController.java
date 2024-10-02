package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.model.FuelStock;
import com.example.fuelmanagement.service.StationManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-owner")
public class StationOwnerController {

    @Autowired
    private StationManagementService stationManagementService;

    @PostMapping("/register")
    public ResponseEntity<StationDTO> registerStation(@Valid @RequestBody StationDTO stationDTO) {
        StationDTO createdStation = stationManagementService.createStation(stationDTO);
        return ResponseEntity.ok(createdStation);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<StationDTO>> getAllStations() {
        return ResponseEntity.ok(stationManagementService.getAllStations());
    }

    @GetMapping("/{stationId}/fuel-stock")
    public List<FuelStock> getFuelStock(@PathVariable Long stationId) {
        return stationManagementService.getAvailableFuelStockByStation(stationId);
    }
}
