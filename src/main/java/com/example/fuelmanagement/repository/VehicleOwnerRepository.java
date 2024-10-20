package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, Long> {
    Optional<VehicleOwner> findByUsername(String username);
}
