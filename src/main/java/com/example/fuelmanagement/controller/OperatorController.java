package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.FuelQuotaDTO;
import com.example.fuelmanagement.service.FuelQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    @Autowired
    private FuelQuotaService fuelQuotaService;

    @GetMapping("/quota/{vehicleId}")
    public ResponseEntity<FuelQuotaDTO> getFuelQuota(@PathVariable Long vehicleId) {
        FuelQuotaDTO fuelQuotaDTO = fuelQuotaService.getFuelQuotaByVehicleId(vehicleId);
        return ResponseEntity.ok(fuelQuotaDTO);
    }

    @PostMapping("/update-quota/{vehicleId}")
    public ResponseEntity<Void> updateFuelQuota(@PathVariable Long vehicleId, @RequestParam int litres) {
        fuelQuotaService.updateFuelQuota(vehicleId, litres);
        return ResponseEntity.ok().build();
    }
}
