package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.PumpingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PumpingRecordRepository extends JpaRepository<PumpingRecord, Long> {
}
