package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.JwtResponse;
import com.example.fuelmanagement.dto.LoginRequest;
import com.example.fuelmanagement.dto.RegisterRequest;
import com.example.fuelmanagement.service.AuthService;
import com.example.fuelmanagement.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest request) {
        String token = authService.authenticateAndGenerateToken(request);
        return ResponseEntity.ok(new JwtResponse(token, request.getUsername(), "ROLE_VEHICLE_OWNER"));
    }
}
