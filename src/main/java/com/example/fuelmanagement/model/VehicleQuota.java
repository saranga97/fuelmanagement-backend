package com.fuelmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle_quota")
public class VehicleQuota {

    @Id
    private String vehicleClass;

    private double weeklyQuota;

    public VehicleQuota() {
    }

    public VehicleQuota(String vehicleClass, double weeklyQuota) {
        this.vehicleClass = vehicleClass;
        this.weeklyQuota = weeklyQuota;
    }

    // Getters and Setters
    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public double getWeeklyQuota() {
        return weeklyQuota;
    }

    public void setWeeklyQuota(double weeklyQuota) {
        this.weeklyQuota = weeklyQuota;
    }
}
