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
import org.springframework.scheduling.annotation.Scheduled;
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

        logger.info("Querying DOMTVehicle with: registrationNumber={}, ownershipId={}, engineNumber={}, vehicleClass={}",
                vehicleDTO.getRegistrationNumber(),
                vehicleDTO.getOwnershipId(),
                vehicleDTO.getEngineNumber(),
                vehicleDTO.getVehicleClass());

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

        String vehicleClass = validVehicle.get().getVehicleClass();
        VehicleQuota vehicleQuota = vehicleQuotaRepository
                .findById(vehicleClass)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle class: " + vehicleClass));

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

        vehicle.setRemainingQuota(vehicleQuota.getWeeklyQuota());

        logger.info("Vehicle successfully registered: {}", vehicle);

        return vehicleRepository.save(vehicle);
    }


    public List<Vehicle> getVehiclesByOwner(String idCardNumber) {

        return vehicleRepository.findByOwnershipId(idCardNumber);
    }

    public VehicleOwnerResponseDTO getLoggedInVehicleOwnerDetails(String username) {

        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle owner not found"));

        List<Vehicle> vehicles = vehicleRepository.findByOwnershipId(vehicleOwner.getIdCardNumber());

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

    @Scheduled(cron = "0 0 0 * * MON")
    public void resetWeeklyQuota() {
        logger.info("Resetting weekly quotas for all vehicles...");

        List<Vehicle> allVehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : allVehicles) {
            int weeklyQuota = vehicle.getVehicleQuota().getWeeklyQuota();
            vehicle.setRemainingQuota(weeklyQuota);
            vehicleRepository.save(vehicle);
        }

        logger.info("Weekly quotas reset successfully.");
    }
}
