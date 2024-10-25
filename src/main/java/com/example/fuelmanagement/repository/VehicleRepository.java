package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByOwnershipId(String ownershipId);

    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
