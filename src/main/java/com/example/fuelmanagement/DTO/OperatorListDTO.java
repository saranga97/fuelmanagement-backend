package com.example.fuelmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperatorListDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String stationName;
    private String stationAddress;
}
