package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "station_owners")
public class StationOwner {

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
