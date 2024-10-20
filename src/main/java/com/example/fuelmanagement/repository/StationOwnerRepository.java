package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.StationOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationOwnerRepository extends JpaRepository<StationOwner, Long> {
}
