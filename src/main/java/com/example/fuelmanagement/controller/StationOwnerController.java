package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.service.StationRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-owner")
public class StationOwnerController {

    @Autowired
    private StationRegistrationService stationRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<StationDTO> registerStation(@Valid @RequestBody StationDTO stationDTO) {
        StationDTO createdStation = stationRegistrationService.createStation(stationDTO);
        return ResponseEntity.ok(createdStation);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<StationDTO>> getAllStations() {
        return ResponseEntity.ok(stationRegistrationService.getAllStations());
    }
}
