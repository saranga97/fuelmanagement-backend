package com.example.fuelmanagement.service;

import com.example.fuelmanagement.DTO.VehicleDTO;
import com.example.fuelmanagement.DTO.VehicleOwnerResponseDTO;
import com.example.fuelmanagement.model.*;
import com.example.fuelmanagement.repository.DOMTVehicleRepository;
import com.example.fuelmanagement.repository.VehicleOwnerRepository;
import com.example.fuelmanagement.repository.VehicleQuotaRepository;
import com.example.fuelmanagement.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;
    private final DOMTVehicleRepository domtVehicleRepository;
    private final VehicleQuotaRepository vehicleQuotaRepository;

    private final VehicleOwnerRepository vehicleOwnerRepository;
    public VehicleService(VehicleRepository vehicleRepository, DOMTVehicleRepository domtVehicleRepository, VehicleQuotaRepository vehicleQuotaRepository, VehicleOwnerRepository vehicleOwnerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.domtVehicleRepository = domtVehicleRepository;
        this.vehicleQuotaRepository = vehicleQuotaRepository;
        this.vehicleOwnerRepository = vehicleOwnerRepository;
    }

    public Vehicle registerVehicle(VehicleDTO vehicleDTO) {

        logger.info("Registering vehicle with details: {}", vehicleDTO);

        // Log query parameters to ensure they match
        logger.info("Querying DOMTVehicle with: registrationNumber={}, ownershipId={}, engineNumber={}, vehicleClass={}",
                vehicleDTO.getRegistrationNumber(),
                vehicleDTO.getOwnershipId(),
                vehicleDTO.getEngineNumber(),
                vehicleDTO.getVehicleClass());

        // Validate against domt_vehicles
        Optional<DOMTVehicle> validVehicle = domtVehicleRepository
                .findByRegistrationNumberAndOwnershipIdAndEngineNumberAndVehicleClass(
                        vehicleDTO.getRegistrationNumber(),
                        vehicleDTO.getOwnershipId(),
                        vehicleDTO.getEngineNumber(),
                        vehicleDTO.getVehicleClass());

        if (validVehicle.isEmpty()) {
            logger.error("Vehicle validation failed. No matching vehicle found.");
            throw new IllegalArgumentException("Vehicle details are not valid.");
        }

        logger.info("Vehicle validated successfully: {}", validVehicle.get());

        // Fetch the VehicleQuota entity based on the vehicle class
        String vehicleClass = validVehicle.get().getVehicleClass();
        VehicleQuota vehicleQuota = vehicleQuotaRepository
                .findById(vehicleClass)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle class: " + vehicleClass));

        // Create and save the Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setEngineNumber(vehicleDTO.getEngineNumber());
        vehicle.setManufacturer(vehicleDTO.getManufacturer());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setEngineCapacity(vehicleDTO.getEngineCapacity());
        vehicle.setFuelType(FuelType.valueOf(vehicleDTO.getFuelType().toUpperCase().replace(" ", "_")));
        vehicle.setManufacturedYear(vehicleDTO.getManufacturedYear());
        vehicle.setVehicleQuota(vehicleQuota);
        vehicle.setOwnershipId(vehicleDTO.getOwnershipId());

        logger.info("Vehicle successfully registered: {}", vehicle);

        return vehicleRepository.save(vehicle);
    }


    public List<Vehicle> getVehiclesByOwner(String idCardNumber) {
        // Retrieve vehicles for the given ownership ID (ID card number)
        return vehicleRepository.findByOwnershipId(idCardNumber);
    }


    public VehicleOwnerResponseDTO getLoggedInVehicleOwnerDetails(String username) {
        // Fetch vehicle owner details
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle owner not found"));

        // Fetch all vehicles belonging to this owner by their ID card number
        List<Vehicle> vehicles = vehicleRepository.findByOwnershipId(vehicleOwner.getIdCardNumber());

        // Construct and return the response DTO
        return new VehicleOwnerResponseDTO(
                vehicleOwner.getId(),
                vehicleOwner.getFullName(),
                vehicleOwner.getEmail(),
                vehicleOwner.getUsername(),
                vehicleOwner.getIdCardNumber(),
                vehicleOwner.getPhoneNumber(),
                vehicles
        );
    }

}
