package com.example.fuelmanagement.mapper;

import com.example.fuelmanagement.dto.FuelStockDTO;
import com.example.fuelmanagement.model.FuelStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuelStockMapper {

    @Mapping(source = "station.id", target = "stationId") // Map station's ID
    FuelStockDTO fuelStockToFuelStockDTO(FuelStock fuelStock);

    @Mapping(target = "station", ignore = true) // Prevent direct mapping of station
    FuelStock fuelStockDTOToFuelStock(FuelStockDTO fuelStockDTO);
}

