package com.example.fuelmanagement.mapper;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);

    StationDTO stationToStationDTO(Station station);
    Station stationDTOToStation(StationDTO stationDTO);
}
