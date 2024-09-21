package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.VehicleDTO;
import com.example.fuelmanagement.mapper.VehicleMapper;
import com.example.fuelmanagement.model.Vehicle;
import com.example.fuelmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleManagementService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.vehicleToVehicleDTO(savedVehicle);
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList());
    }
}
