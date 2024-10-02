package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelStockRepository extends JpaRepository<FuelStock, Long> {
    List<FuelStock> findByStationId(Long stationId);  // Query by station id
}

