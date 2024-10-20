package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.repository.VehicleOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleOwnerService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    public VehicleOwner registerVehicleOwner(VehicleOwner owner) {
        return vehicleOwnerRepository.save(owner);
    }
}
