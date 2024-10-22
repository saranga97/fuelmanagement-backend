package com.example.fuelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "operators",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email", "id_card_number"})})
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 12)
    private String idCardNumber;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_OPERATOR;

    // Getters and setters
}
