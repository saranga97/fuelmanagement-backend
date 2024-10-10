package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    // Custom query to find stations by the owner's ID
    List<Station> findByOwnerId(Long ownerId);
}
