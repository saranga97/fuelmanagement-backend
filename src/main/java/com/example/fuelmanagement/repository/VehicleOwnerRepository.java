package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, String> {
    Optional<VehicleOwner> findByUsername(String username);
    Optional<VehicleOwner> findByIdCardNumber(String idCardNumber);

}


