package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByOwnershipId(String ownershipId);
}
