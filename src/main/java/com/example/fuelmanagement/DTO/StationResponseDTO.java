package com.example.fuelmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StationResponseDTO {
    private String stationName;
    private String stationAddress;
    private String ownerName;
    private String ownerEmail;
    private double dieselQuota;
    private double superDieselQuota;
    private double petrol92Quota;
    private double petrol95Quota;
}
