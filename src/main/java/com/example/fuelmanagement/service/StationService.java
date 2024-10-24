package com.example.fuelmanagement.service;

import com.example.fuelmanagement.DTO.StationWithQuotaDTO;
import com.example.fuelmanagement.model.FuelInventory;
import com.example.fuelmanagement.model.Station;
import com.example.fuelmanagement.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    // Fetch all stations with their remaining quotas
    public List<StationWithQuotaDTO> getAllStationsWithQuotas() {
        List<Station> stations = stationRepository.findAllWithOwners(); // Optimized query with JOIN FETCH

        // Map each station to a StationWithQuotaDTO
        return stations.stream()
                .map(station -> {
                    FuelInventory inventory = station.getFuelInventory();
                    return new StationWithQuotaDTO(
                            station.getStationId(),
                            station.getName(),
                            station.getAddress(),
                            inventory.getDieselQuota(),
                            inventory.getSuperDieselQuota(),
                            inventory.getPetrol92Quota(),
                            inventory.getPetrol95Quota()
                    );
                })
                .collect(Collectors.toList());
    }
}
