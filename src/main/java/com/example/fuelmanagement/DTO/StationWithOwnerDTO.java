package com.example.fuelmanagement.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationWithOwnerDTO {
    private String stationName;
    private String stationAddress;
    private String ownerName;
    private String ownerEmail;

    public StationWithOwnerDTO(String stationName, String stationAddress, String ownerName, String ownerEmail) {
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
    }
}
