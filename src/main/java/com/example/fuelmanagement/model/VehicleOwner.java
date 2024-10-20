package com.example.fuelmanagement.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "vehicle_owners")
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String idCardNumber;
    private String phoneNumber;
}
