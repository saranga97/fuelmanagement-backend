package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.dto.UserDTO;
import com.example.fuelmanagement.mapper.StationMapper;
import com.example.fuelmanagement.model.FuelStock;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.model.USER_ROLE;
import com.example.fuelmanagement.model.User;
import com.example.fuelmanagement.repository.FuelStockRepository;
import com.example.fuelmanagement.repository.StationRepository;
import com.example.fuelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationManagementService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private FuelStockRepository fuelStockRepository;

//    @Autowired
//    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * Signup
     * login
     * Register station
     * view all stations
     * view fuel stock in stations
     */

//    // Method to register a user (signup)
//    public UserDTO signup(UserDTO userDTO) {
//        // Check if the password is null or empty
//        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
//        userDTO.setRole(USER_ROLE.ROLE_FUEL_STATION_OWNER);  // Assign default role for new signups
//        return authenticationService.registerUser(userDTO);  // Call authentication service to register user
//    }

    // Method to register a station by a user
    public StationDTO registerStation(StationDTO stationDTO) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Fetch username of logged-in user

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ensure the user is a Fuel Station Owner
        if (!currentUser.getRole().equals(USER_ROLE.ROLE_FUEL_STATION_OWNER)) {
            throw new RuntimeException("User does not have permission to register a station");
        }

        // Create a new station and associate it with the user
        Station station = stationMapper.stationDTOToStation(stationDTO);
        station.setOwner(currentUser); // Set the current user as the owner of the station
        Station savedStation = stationRepository.save(station);

        return stationMapper.stationToStationDTO(savedStation); // Return saved station as DTO
    }

//    public StationDTO createStation(StationDTO stationDTO) {
//        Station station = stationMapper.stationDTOToStation(stationDTO);
//        Station savedStation = stationRepository.save(station);
//        return stationMapper.stationToStationDTO(savedStation);
//    }

    // Method to view stations by user role
    public List<StationDTO> viewStationsByUser() {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Fetch username of logged-in user

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user is an Admin
        if (currentUser.getRole().equals(USER_ROLE.ROLE_ADMIN)) {
            // Admin can view all stations
            return stationRepository.findAll()
                    .stream()
                    .map(stationMapper::stationToStationDTO)
                    .collect(Collectors.toList());
        } else if (currentUser.getRole().equals(USER_ROLE.ROLE_FUEL_STATION_OWNER)) {
            // Fuel Station Owner can only view their own stations
            return stationRepository.findByOwnerId(currentUser.getId())
                    .stream()
                    .map(stationMapper::stationToStationDTO)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("User does not have permission to view stations");
        }
    }

    public List<StationDTO> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(stationMapper::stationToStationDTO)
                .collect(Collectors.toList());
    }

    public List<FuelStock> getAvailableFuelStockByStation(Long stationId) {
        return fuelStockRepository.findByStationId(stationId);
    }

}
