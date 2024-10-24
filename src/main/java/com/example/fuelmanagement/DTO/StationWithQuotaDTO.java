package com.example.fuelmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StationWithQuotaDTO {
    private Long stationId;
    private String name;
    private String address;
    private double dieselQuota;
    private double superDieselQuota;
    private double petrol92Quota;
    private double petrol95Quota;
}
