package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.FuelStockDTO;
import com.example.fuelmanagement.mapper.FuelStockMapper;
import com.example.fuelmanagement.model.FuelStock;
import com.example.fuelmanagement.model.Station;
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
public class FuelStockService {

    @Autowired
    private FuelStockRepository fuelStockRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FuelStockMapper fuelStockMapper;

    // Method to add fuel stock for a specific station
    public FuelStockDTO addFuelStock(FuelStockDTO fuelStockDTO) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the station owned by the current user
        Station station = stationRepository.findById(fuelStockDTO.getStationId())
                .orElseThrow(() -> new RuntimeException("Station not found"));

        if (!station.getOwner().getId().equals(currentUser.getId())) {
            throw new RuntimeException("User does not have permission to add stock for this station");
        }

        // Create a new FuelStock entity and save it
        FuelStock fuelStock = fuelStockMapper.fuelStockDTOToFuelStock(fuelStockDTO);
        fuelStock.setStation(station);  // Associate the stock with the station
        FuelStock savedFuelStock = fuelStockRepository.save(fuelStock);

        return fuelStockMapper.fuelStockToFuelStockDTO(savedFuelStock);
    }

    // Method to view available fuel stock for a specific station owned by the user
    public List<FuelStockDTO> viewFuelStockByStation(Long stationId) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the station owned by the current user
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        if (!station.getOwner().getId().equals(currentUser.getId())) {
            throw new RuntimeException("User does not have permission to view stock for this station");
        }

        // Retrieve available fuel stock for the station
        return fuelStockRepository.findByStationId(stationId).stream()
                .map(fuelStockMapper::fuelStockToFuelStockDTO)
                .collect(Collectors.toList());
    }
}
