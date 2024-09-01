package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String identityCardNumber;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role = USER_ROLE.ROLE_VEHICLE_OWNER;


}
