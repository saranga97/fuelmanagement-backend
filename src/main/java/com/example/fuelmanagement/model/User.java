package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String fullName;
    @Getter
    private String email;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String identityCardNumber;

    @Getter
    @Enumerated(EnumType.STRING)
    private USER_ROLE role = USER_ROLE.ROLE_VEHICLE_OWNER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }
}
