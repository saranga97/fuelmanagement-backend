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
}
