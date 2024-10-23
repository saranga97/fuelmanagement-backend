package com.example.fuelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "fuel_inventory")
public class FuelInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters and setters
    @Getter
    @Column(name = "diesel_quota", nullable = false)
    private double dieselQuota;

    @Getter
    @Column(name = "super_diesel_quota", nullable = false)
    private double superDieselQuota;

    @Getter
    @Column(name = "petrol_92_quota", nullable = false)
    private double petrol92Quota;

    @Getter
    @Column(name = "petrol_95_quota", nullable = false)
    private double petrol95Quota;

    @Getter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", referencedColumnName = "stationId")
    private Station station;

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

    public void setStation(Station station) {
        this.station = station;
    }
}
