package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.*;
import com.example.fuelmanagement.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class StationOperatorService {

    private final VehicleRepository vehicleRepository;
    private final FuelInventoryRepository fuelInventoryRepository;
    private final PumpingRecordRepository pumpingRecordRepository;
    private final OperatorRepository operatorRepository;
    private final VehicleOwnerRepository vehicleOwnerRepository;

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    public StationOperatorService(VehicleRepository vehicleRepository,
                                  FuelInventoryRepository fuelInventoryRepository,
                                  PumpingRecordRepository pumpingRecordRepository,
                                  OperatorRepository operatorRepository,
                                  VehicleOwnerRepository vehicleOwnerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.fuelInventoryRepository = fuelInventoryRepository;
        this.pumpingRecordRepository = pumpingRecordRepository;
        this.operatorRepository = operatorRepository;
        this.vehicleOwnerRepository = vehicleOwnerRepository;
    }

    @PostConstruct
    public void initializeTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public void pumpFuel(Long vehicleId, Long operatorId, double liters, FuelType fuelType, Station station) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new IllegalArgumentException("Operator not found"));

        FuelInventory inventory = fuelInventoryRepository.findByStation(station)
                .orElseThrow(() -> new IllegalArgumentException("Station inventory not found"));

        if (vehicle.getRemainingQuota() < liters) {
            throw new IllegalArgumentException("Vehicle's remaining quota is insufficient");
        }

        switch (fuelType) {
            case DIESEL -> inventory.setDieselQuota(inventory.getDieselQuota() - liters);
            case SUPER_DIESEL -> inventory.setSuperDieselQuota(inventory.getSuperDieselQuota() - liters);
            case PETROL_92_OCTANE -> inventory.setPetrol92Quota(inventory.getPetrol92Quota() - liters);
            case PETROL_95_OCTANE -> inventory.setPetrol95Quota(inventory.getPetrol95Quota() - liters);
        }

        vehicle.setRemainingQuota(vehicle.getRemainingQuota() - liters);

        PumpingRecord record = new PumpingRecord();
        record.setOperator(operator);
        record.setStation(station);
        record.setVehicle(vehicle);
        record.setLitersPumped(liters);
        record.setFuelType(fuelType);

        pumpingRecordRepository.save(record);

        VehicleOwner owner = vehicleOwnerRepository.findByIdCardNumber(vehicle.getOwnershipId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle owner not found"));

        sendSMS(owner.getPhoneNumber(), liters, fuelType);
    }

    private void sendSMS(String phoneNumber, double liters, FuelType fuelType) {
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+94" + phoneNumber.substring(1);
        }

        Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                String.format("You pumped %.2f liters of %s", liters, fuelType)
        ).create();
    }

    public Operator getOperatorByUsername(String username) {
        return operatorRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Operator not found"));
    }
}
