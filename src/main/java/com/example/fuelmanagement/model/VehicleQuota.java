package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "vehicle_quota")
public class VehicleQuota {

    @Id
    @Column(name = "vehicle_class")
    private String vehicleClass;

    private int weeklyQuota;

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setWeeklyQuota(int weeklyQuota) {
        this.weeklyQuota = weeklyQuota;
    }
}
