package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.model.StationOwner;
import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.repository.StationOwnerRepository;
import com.example.fuelmanagement.repository.StationRepository;
import com.example.fuelmanagement.repository.VehicleOwnerRepository;
import com.example.fuelmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {


    private final StationRepository stationRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final VehicleOwnerRepository vehicleOwnerRepository;
    private final VehicleRepository vehicleRepository;

    public AdminService(StationRepository stationRepository, StationOwnerRepository stationOwnerRepository,
                        VehicleOwnerRepository vehicleOwnerRepository,
                        VehicleRepository vehicleRepository) {
        this.stationRepository = stationRepository;
        this.stationOwnerRepository = stationOwnerRepository;
        this.vehicleOwnerRepository = vehicleOwnerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // Retrieve all station owners
    public List<StationOwner> getAllStationOwners() {
        return stationOwnerRepository.findAll();
    }

    // Retrieve all vehicle owners
    public List<VehicleOwner> getAllVehicleOwners() {
        return vehicleOwnerRepository.findAll();
    }

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

//    public Station registerStation(String name, String address, Long stationOwnerId) {
//        Optional<StationOwner> ownerOptional = stationOwnerRepository.findById(stationOwnerId);
//
//        if (ownerOptional.isEmpty()) {
//            throw new IllegalArgumentException("Station owner not found with ID: " + stationOwnerId);
//        }
//
//        StationOwner stationOwner = ownerOptional.get();
//        Station station = new Station(name, address, stationOwner);
//        return stationRepository.save(station);
//    }

    public Station registerStation(String name, String address, Long stationOwnerId) {
        StationOwner owner = stationOwnerRepository.findById(stationOwnerId)
                .orElseThrow(() -> new IllegalArgumentException("Station owner not found"));

        Station station = new Station(name, address, owner);
        return stationRepository.save(station);
    }

}
