package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.FuelInventory;
import com.example.fuelmanagement.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelInventoryRepository extends JpaRepository<FuelInventory, Long> {
    Optional<FuelInventory> findByStation(Station station);
}
