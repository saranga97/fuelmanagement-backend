package com.example.fuelmanagement.service;

import com.example.fuelmanagement.DTO.OperatorDTO;
import com.example.fuelmanagement.DTO.OperatorListDTO;
import com.example.fuelmanagement.DTO.OperatorResponseDTO;
import com.example.fuelmanagement.model.FuelInventory;
import com.example.fuelmanagement.model.Operator;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.model.UserRole;
import com.example.fuelmanagement.repository.FuelInventoryRepository;
import com.example.fuelmanagement.repository.OperatorRepository;
import com.example.fuelmanagement.repository.StationRepository;
import com.example.fuelmanagement.repository.StationOwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationOwnerService {

    private final StationRepository stationRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final FuelInventoryRepository fuelInventoryRepository;

    private final OperatorRepository operatorRepository;

    private final PasswordEncoder passwordEncoder;

    public StationOwnerService(StationRepository stationRepository,
                               StationOwnerRepository stationOwnerRepository,
                               FuelInventoryRepository fuelInventoryRepository, OperatorRepository operatorRepository, PasswordEncoder passwordEncoder) {
        this.stationRepository = stationRepository;
        this.stationOwnerRepository = stationOwnerRepository;
        this.fuelInventoryRepository = fuelInventoryRepository;
        this.operatorRepository = operatorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieve all stations owned by the station owner with the given username.
     */
    public List<Station> getStationsByOwnerUsername(String username) {
        return stationRepository.findByStationOwner_Username(username);
    }

    /**
     * Retrieve the fuel inventory for a specific station owned by the given station owner.
     */
    public FuelInventory getFuelInventoryForStation(Long stationId, String ownerUsername) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found with ID: " + stationId));

        // Ensure the station belongs to the logged-in owner
        if (!station.getStationOwner().getUsername().equals(ownerUsername)) {
            throw new SecurityException("Access denied. This station does not belong to you.");
        }

        return fuelInventoryRepository.findByStation(station)
                .orElseThrow(() -> new IllegalArgumentException("Fuel inventory not found for station ID: " + stationId));
    }

    public OperatorResponseDTO registerOperator(OperatorDTO operatorDTO) {
        // Find the station by ID
        Station station = stationRepository.findById(operatorDTO.getStationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid station ID"));

        // Create a new Operator and encode the password
        Operator operator = new Operator();
        operator.setFullName(operatorDTO.getFullName());
        operator.setEmail(operatorDTO.getEmail());
        operator.setUsername(operatorDTO.getUsername());
        operator.setPassword(passwordEncoder.encode(operatorDTO.getPassword()));  // Encode here!
        operator.setIdCardNumber(operatorDTO.getIdCardNumber());
        operator.setPhoneNumber(operatorDTO.getPhoneNumber());
        operator.setStation(station);
        operator.setRole(UserRole.ROLE_OPERATOR);

        Operator savedOperator = operatorRepository.save(operator);

        return new OperatorResponseDTO(
                savedOperator.getId(),
                savedOperator.getFullName(),
                savedOperator.getEmail(),
                savedOperator.getUsername(),
                savedOperator.getRole(),
                savedOperator.getStation()
        );
    }

    public List<OperatorListDTO> getAllOperatorsForOwner(String ownerUsername) {

        List<Station> stations = stationRepository.findByStationOwner_Username(ownerUsername);

        return stations.stream()
                .flatMap(station -> station.getOperators().stream())
                .map(operator -> new OperatorListDTO(
                        operator.getId(),
                        operator.getFullName(),
                        operator.getEmail(),
                        operator.getPhoneNumber(),
                        operator.getUsername(),
                        operator.getStation().getName(),
                        operator.getStation().getAddress()
                ))
                .collect(Collectors.toList());
    }

}
