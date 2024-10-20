package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@RequestBody VehicleOwner owner) {
        VehicleOwner savedOwner = ownerService.registerVehicleOwner(owner);
        return ResponseEntity.ok(savedOwner);
    }
}
