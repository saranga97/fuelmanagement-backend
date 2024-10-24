package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByUsername(String username);
}
