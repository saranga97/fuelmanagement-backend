package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.*;
import com.example.fuelmanagement.config.JwtUtils;
import com.example.fuelmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register/vehicle-owner")
    public ResponseEntity<?> registerVehicleOwner(@RequestBody @Valid VehicleOwnerDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveVehicleOwner(dto);
        return ResponseEntity.ok("Vehicle owner registered successfully!");
    }

    @PostMapping("/register/station-owner")
    public ResponseEntity<?> registerStationOwner(@RequestBody @Valid StationOwnerDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveStationOwner(dto);
        return ResponseEntity.ok("Station owner registered successfully!");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid AdminDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveAdmin(dto);
        return ResponseEntity.ok("Admin registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt));
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }
}

