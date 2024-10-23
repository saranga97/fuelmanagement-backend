package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.VehicleQuota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleQuotaRepository extends JpaRepository<VehicleQuota, String> {
}
