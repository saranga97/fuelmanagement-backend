package com.example.fuelmanagement.dto;

import com.example.fuelmanagement.model.USER_ROLE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Identity card number is required")
    @Size(min = 10, max = 12, message = "Identity card number should be between 10 and 12 characters")
    private String identityCardNumber;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    private USER_ROLE role;

    @NotBlank(message = "Username is required")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Full name is required") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Full name is required") String fullName) {
        this.fullName = fullName;
    }

    public @Email(message = "Email should be valid") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Identity card number is required") @Size(min = 10, max = 12, message = "Identity card number should be between 10 and 12 characters") String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(@NotBlank(message = "Identity card number is required") @Size(min = 10, max = 12, message = "Identity card number should be between 10 and 12 characters") String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Role is required") USER_ROLE getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required") USER_ROLE role) {
        this.role = role;}

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }
}
