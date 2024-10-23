package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.DTO.*;
import com.example.fuelmanagement.config.JwtUtils;
import com.example.fuelmanagement.model.Admin;
import com.example.fuelmanagement.service.UserService;
import com.example.fuelmanagement.service.VehicleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new vehicle owner.
     */
    @PostMapping("/register/vehicle-owner")
    public ResponseEntity<?> registerVehicleOwner(@RequestBody @Valid VehicleOwnerDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveVehicleOwner(dto);
        return ResponseEntity.ok("Vehicle owner registered successfully!");
    }

    /**
     * Register a new station owner.
     */
    @PostMapping("/register/station-owner")
    public ResponseEntity<?> registerStationOwner(@RequestBody @Valid StationOwnerDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveStationOwner(dto);
        return ResponseEntity.ok("Station owner registered successfully!");
    }

    /**
     * Register a new admin. Note that only one admin should exist.
     */
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid AdminDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.saveAdmin(dto);
        return ResponseEntity.ok("Admin registered successfully!");
    }

    /**
     * Login endpoint for regular users (Vehicle and Station Owners).
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
          //  logger.info();
            return ResponseEntity.ok(new JwtResponse(jwt));
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }

    /**
     * Login endpoint for admin users.
     */
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody @Valid AdminLoginDTO dto) {
        try {
            Admin admin = userService.loadAdminByUsername(dto.getUsername());

            // Check if the provided password matches
            if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
                return ResponseEntity.status(401).body("Invalid password.");
            }

            // Ensure the user has the correct role
            if (!admin.getRole().name().equals("ROLE_ADMIN")) {
                return ResponseEntity.status(403).body("Access denied. User does not have admin privileges.");
            }

            // Generate JWT token if authentication succeeds
            String jwt = jwtUtils.generateJwtToken(admin.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Admin not found with username: " + dto.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal error occurred.");
        }
    }

}
