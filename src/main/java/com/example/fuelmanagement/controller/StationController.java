package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.StationWithQuotaDTO;
import com.example.fuelmanagement.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    // Get all stations with their fuel quotas
    @PostMapping("/all-with-quotas")
    public ResponseEntity<List<StationWithQuotaDTO>> getAllStationsWithQuotas() {
        List<StationWithQuotaDTO> stations = stationService.getAllStationsWithQuotas();
        return ResponseEntity.ok(stations);
    }
}
