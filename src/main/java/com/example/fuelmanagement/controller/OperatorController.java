package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.*;
import com.example.fuelmanagement.model.USER_ROLE;
import com.example.fuelmanagement.security.JwtTokenUtil;
import com.example.fuelmanagement.service.AuthenticationService;
import com.example.fuelmanagement.service.FuelQuotaService;
import com.example.fuelmanagement.service.FuelStockService;
import com.example.fuelmanagement.service.StationManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    @Autowired
    private StationManagementService stationManagementService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FuelStockService fuelStockService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // User Signup
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        userDTO.setRole(USER_ROLE.ROLE_OPERATOR);  // Assign default role for new signups
        // Call authentication service to register user
        return ResponseEntity.ok(authenticationService.registerUser(userDTO));
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // Set authentication to security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtTokenUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(jwt);
    }

}
