package com.example.fuelmanagement.mapper;

import com.example.fuelmanagement.dto.VehicleDTO;
import com.example.fuelmanagement.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);
    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
}
