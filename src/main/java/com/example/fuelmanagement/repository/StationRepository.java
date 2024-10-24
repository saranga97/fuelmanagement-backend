package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    // Fetch stations along with their station owners
    @Query("SELECT s FROM Station s JOIN FETCH s.stationOwner JOIN FETCH s.fuelInventory")
    List<Station> findAllWithOwners();

    List<Station> findByStationOwner_Username(String username);
}
