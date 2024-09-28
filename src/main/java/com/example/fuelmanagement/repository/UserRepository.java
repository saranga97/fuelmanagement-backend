package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods if needed
}
