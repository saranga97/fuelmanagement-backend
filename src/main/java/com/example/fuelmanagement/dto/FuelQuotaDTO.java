package com.example.fuelmanagement.dto;

public class FuelQuotaDTO {
    private Long vehicleId;
    private String registrationNumber;
    private int remainingQuota;

    // Constructor
    public FuelQuotaDTO(Long vehicleId, String registrationNumber, int remainingQuota) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.remainingQuota = remainingQuota;
    }

    // Getters and Setters
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }
}
