package com.example.fuelmanagement.controller;

import com.example.fuelmanagement.dto.UserDTO;
import com.example.fuelmanagement.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminManagementService adminManagementService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminManagementService.getAllUsers());
    }

    // Other administrative endpoints
}
