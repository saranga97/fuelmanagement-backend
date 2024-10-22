package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.DOMTVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DOMTVehicleRepository extends JpaRepository<DOMTVehicle, Long> {
    Optional<DOMTVehicle> findByRegistrationNumberAndOwnershipIdAndEngineNumberAndVehicleClass(
            String registrationNumber, String ownershipId, String engineNumber, String vehicleClass);
}
