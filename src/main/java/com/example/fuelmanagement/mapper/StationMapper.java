package com.example.fuelmanagement.mapper;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);

    @Mapping(source = "owner.username", target = "ownerUsername")
    StationDTO stationToStationDTO(Station station);

    @Mapping(target = "owner", ignore = true) // Prevent direct mapping of owner
    Station stationDTOToStation(StationDTO stationDTO);
}
