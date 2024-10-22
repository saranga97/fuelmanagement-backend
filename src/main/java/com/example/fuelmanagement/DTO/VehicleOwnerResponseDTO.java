package com.example.fuelmanagement.DTO;

import com.example.fuelmanagement.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class VehicleOwnerResponseDTO {
    private Long userId;
    private String fullName;
    private String email;
    private String username;
    private String idCardNumber;
    private String phoneNumber;
    private List<Vehicle> vehicles;  // List of vehicles owned
}
