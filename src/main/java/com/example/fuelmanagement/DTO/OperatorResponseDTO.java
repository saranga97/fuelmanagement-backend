package com.example.fuelmanagement.DTO;

import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperatorResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String phoneNumber;
    private String stationName;
    private String stationAddress;


    public OperatorResponseDTO(Long id, String fullName, String email, String username, UserRole role, Station station) {
    }
}
