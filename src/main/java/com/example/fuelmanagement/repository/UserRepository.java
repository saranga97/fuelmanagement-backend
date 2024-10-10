package com.example.fuelmanagement.repository;

import com.example.fuelmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find user by username
    Optional<User> findByUsername(String username);
}
