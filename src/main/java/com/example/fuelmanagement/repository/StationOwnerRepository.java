package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.StationOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationOwnerRepository extends JpaRepository<StationOwner, Long> {
    Optional<StationOwner> findByUsername(String username);
}
