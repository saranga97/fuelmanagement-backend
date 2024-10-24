package com.example.fuelmanagement.service;

import com.example.fuelmanagement.DTO.FuelInventoryUpdateDTO;
import com.example.fuelmanagement.DTO.StationWithOwnerDTO;
import com.example.fuelmanagement.model.*;
import com.example.fuelmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {


    private final StationRepository stationRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final VehicleOwnerRepository vehicleOwnerRepository;
    private final VehicleRepository vehicleRepository;

    private final FuelInventoryRepository fuelInventoryRepository;

    public AdminService(StationRepository stationRepository, StationOwnerRepository stationOwnerRepository,
                        VehicleOwnerRepository vehicleOwnerRepository,
                        VehicleRepository vehicleRepository, FuelInventoryRepository fuelInventoryRepository) {
        this.stationRepository = stationRepository;
        this.stationOwnerRepository = stationOwnerRepository;
        this.vehicleOwnerRepository = vehicleOwnerRepository;
        this.vehicleRepository = vehicleRepository;
        this.fuelInventoryRepository = fuelInventoryRepository;
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

    public List<Station> getAllStations() {
        return stationRepository.findAll();  // Fetch all stations with owners
    }

    public FuelInventory getFuelInventoryByStation(Station station) {
        return fuelInventoryRepository.findByStation(station)
                .orElseThrow(() -> new IllegalArgumentException("Fuel inventory not found for station: " + station.getName()));
    }

    public List<StationWithOwnerDTO> getAllStationsWithOwners() {
        List<Station> stations = stationRepository.findAllWithOwners();
        List<StationWithOwnerDTO> stationDTOs = new ArrayList<>();

        for (Station station : stations) {
            stationDTOs.add(new StationWithOwnerDTO(
                    station.getName(),
                    station.getAddress(),
                    station.getStationOwner().getFullName(),
                    station.getStationOwner().getEmail()
            ));
        }

        return stationDTOs;
    }

    public void updateFuelQuota(FuelInventoryUpdateDTO fuelInventoryUpdateDTO) {
        Long stationId = fuelInventoryUpdateDTO.getStationId();

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found with ID: " + stationId));

        FuelInventory fuelInventory = fuelInventoryRepository.findByStation(station)
                .orElseThrow(() -> new IllegalArgumentException("Fuel inventory not found for station: " + station.getName()));

        fuelInventory.setDieselQuota(fuelInventoryUpdateDTO.getDieselQuota());
        fuelInventory.setSuperDieselQuota(fuelInventoryUpdateDTO.getSuperDieselQuota());
        fuelInventory.setPetrol92Quota(fuelInventoryUpdateDTO.getPetrol92Quota());
        fuelInventory.setPetrol95Quota(fuelInventoryUpdateDTO.getPetrol95Quota());


        fuelInventoryRepository.save(fuelInventory);
    }

    public Station registerStation(String name, String address, Long stationOwnerId) {
        StationOwner owner = stationOwnerRepository.findById(stationOwnerId)
                .orElseThrow(() -> new IllegalArgumentException("Station owner not found"));

        Station station = new Station(name, address, owner);
        return stationRepository.save(station);
    }

}
