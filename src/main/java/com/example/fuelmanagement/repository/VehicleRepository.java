package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Additional query methods if needed
}
