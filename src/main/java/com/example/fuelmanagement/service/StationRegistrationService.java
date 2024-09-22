package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.StationDTO;
import com.example.fuelmanagement.mapper.StationMapper;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationRegistrationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMapper stationMapper;

    public StationDTO createStation(StationDTO stationDTO) {
        Station station = stationMapper.stationDTOToStation(stationDTO);
        Station savedStation = stationRepository.save(station);
        return stationMapper.stationToStationDTO(savedStation);
    }

    public List<StationDTO> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(stationMapper::stationToStationDTO)
                .collect(Collectors.toList());
    }
}
