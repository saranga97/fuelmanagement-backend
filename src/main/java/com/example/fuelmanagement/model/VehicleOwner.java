package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle_owners")
@Data
public class VehicleOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String idCardNumber;
    private String phoneNumber;
}
