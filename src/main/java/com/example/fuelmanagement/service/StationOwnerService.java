package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.FuelInventory;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.repository.FuelInventoryRepository;
import com.example.fuelmanagement.repository.StationRepository;
import com.example.fuelmanagement.repository.StationOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationOwnerService {

    private final StationRepository stationRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final FuelInventoryRepository fuelInventoryRepository;

    public StationOwnerService(StationRepository stationRepository,
                               StationOwnerRepository stationOwnerRepository,
                               FuelInventoryRepository fuelInventoryRepository) {
        this.stationRepository = stationRepository;
        this.stationOwnerRepository = stationOwnerRepository;
        this.fuelInventoryRepository = fuelInventoryRepository;
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
}
