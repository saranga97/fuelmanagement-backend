package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "fuel_inventory")
public class FuelInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", referencedColumnName = "stationId", nullable = false)
    private Station station;

    @Column(name = "diesel_quota", nullable = false)
    private double dieselQuota;

    @Column(name = "super_diesel_quota", nullable = false)
    private double superDieselQuota;

    @Column(name = "petrol_92_quota", nullable = false)
    private double petrol92Quota;

    @Column(name = "petrol_95_quota", nullable = false)
    private double petrol95Quota;

    public void setId(Long id) {
        this.id = id;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setDieselQuota(double dieselQuota) {
        this.dieselQuota = dieselQuota;
    }

    public void setSuperDieselQuota(double superDieselQuota) {
        this.superDieselQuota = superDieselQuota;
    }

    public void setPetrol92Quota(double petrol92Quota) {
        this.petrol92Quota = petrol92Quota;
    }

    public void setPetrol95Quota(double petrol95Quota) {
        this.petrol95Quota = petrol95Quota;
    }
// Getters and Setters
}
