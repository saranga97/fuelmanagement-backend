package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
