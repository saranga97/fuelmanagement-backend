package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.LoginDTO;
import com.example.fuelmanagement.dto.UserDTO;
import com.example.fuelmanagement.model.USER_ROLE;
import com.example.fuelmanagement.security.JwtTokenUtil;
import com.example.fuelmanagement.service.AuthenticationService;
import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.model.FuelStock;
import com.example.fuelmanagement.service.StationManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-owner")
public class StationOwnerController {

    @Autowired
    private StationManagementService stationManagementService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // User Registration (Signup)
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
//        UserDTO registeredUser = stationManagementService.signup(userDTO);
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        userDTO.setRole(USER_ROLE.ROLE_FUEL_STATION_OWNER);  // Assign default role for new signups
        // Call authentication service to register user
        return ResponseEntity.ok(authenticationService.registerUser(userDTO));
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

        System.out.println("Authenticated user: " + authentication.getName());
        // Set authentication to security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtTokenUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(jwt);
    }


    // Endpoint to register a station
    // Register station (Restricted to ROLE_FUEL_STATION_OWNER)
    @PreAuthorize("hasRole('ROLE_FUEL_STATION_OWNER')")
    @PostMapping("/register")
    public ResponseEntity<StationDTO> registerStation(@RequestBody StationDTO stationDTO) {
        StationDTO registeredStation = stationManagementService.registerStation(stationDTO);
        return ResponseEntity.ok(registeredStation);
    }

    // Endpoint to view stations by the logged-in user
    // View stations by the logged-in user (Restricted to ROLE_FUEL_STATION_OWNER)
    @PreAuthorize("hasRole('ROLE_FUEL_STATION_OWNER')")
    @GetMapping("/my-stations")
    public ResponseEntity<List<StationDTO>> viewStationsByUser() {
        List<StationDTO> stations = stationManagementService.viewStationsByUser();
        return ResponseEntity.ok(stations);
    }

    // View all stations (Restricted to ROLE_ADMIN or ROLE_FUEL_STATION_OWNER)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FUEL_STATION_OWNER')")
    @GetMapping("/stations")
    public ResponseEntity<List<StationDTO>> getAllStations() {
        return ResponseEntity.ok(stationManagementService.getAllStations());
    }

    // View fuel stock by station (Restricted to ROLE_ADMIN or ROLE_FUEL_STATION_OWNER)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FUEL_STATION_OWNER')")
    @GetMapping("/{stationId}/fuel-stock")
    public List<FuelStock> getFuelStock(@PathVariable Long stationId) {
        return stationManagementService.getAvailableFuelStockByStation(stationId);
    }
}
