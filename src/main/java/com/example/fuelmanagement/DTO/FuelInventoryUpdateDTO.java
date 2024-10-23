package com.example.fuelmanagement.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelInventoryUpdateDTO {
    private Long stationId;
    private double dieselQuota;
    private double superDieselQuota;
    private double petrol92Quota;
    private double petrol95Quota;
}
