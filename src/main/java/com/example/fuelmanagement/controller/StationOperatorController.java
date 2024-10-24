package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.PumpFuelRequest;
import com.example.fuelmanagement.model.*;
import com.example.fuelmanagement.repository.StationRepository;
import com.example.fuelmanagement.service.StationOperatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operators")
public class StationOperatorController {

    private final StationOperatorService operatorService;

    private final StationRepository stationRepository;

    public StationOperatorController(StationOperatorService operatorService, StationRepository stationRepository) {
        this.operatorService = operatorService;
        this.stationRepository = stationRepository;
    }

    @PostMapping("/pump-fuel")
    public ResponseEntity<String> pumpFuel(
            @RequestBody PumpFuelRequest pumpFuelRequest,
            @AuthenticationPrincipal UserDetails userDetails) {

        Operator operator = operatorService.getOperatorByUsername(userDetails.getUsername());

        Station station = stationRepository.findById(operator.getStation().getStationId())
                .orElseThrow(() -> new IllegalArgumentException("Station not found"));

        operatorService.pumpFuel(
                pumpFuelRequest.getVehicleId(),
                operator.getId(),
                pumpFuelRequest.getLitersPumped(),
                pumpFuelRequest.getFuelType(),
                station
        );

        return ResponseEntity.ok("Fuel pumped successfully and SMS sent.");
    }


}
