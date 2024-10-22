package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.Admin;
import com.example.fuelmanagement.model.StationOwner;
import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.DTO.AdminDTO;
import com.example.fuelmanagement.DTO.StationOwnerDTO;
import com.example.fuelmanagement.DTO.VehicleOwnerDTO;
import com.example.fuelmanagement.repository.AdminRepository;
import com.example.fuelmanagement.repository.StationOwnerRepository;
import com.example.fuelmanagement.repository.VehicleOwnerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final VehicleOwnerRepository vehicleOwnerRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final AdminRepository adminRepository;

    public UserService(VehicleOwnerRepository vehicleOwnerRepository,
                       StationOwnerRepository stationOwnerRepository,
                       AdminRepository adminRepository) {
        this.vehicleOwnerRepository = vehicleOwnerRepository;
        this.stationOwnerRepository = stationOwnerRepository;
        this.adminRepository = adminRepository;
    }

    // Save Vehicle Owner
    public void saveVehicleOwner(VehicleOwnerDTO dto) {
        VehicleOwner vehicleOwner = new VehicleOwner();
        vehicleOwner.setFullName(dto.getFullName());
        vehicleOwner.setEmail(dto.getEmail());
        vehicleOwner.setUsername(dto.getUsername());
        vehicleOwner.setPassword(dto.getPassword());
        vehicleOwner.setIdCardNumber(dto.getIdCardNumber());
        vehicleOwner.setPhoneNumber(dto.getPhoneNumber());
        vehicleOwnerRepository.save(vehicleOwner);
    }

    // Save Station Owner
    public void saveStationOwner(StationOwnerDTO dto) {
        StationOwner stationOwner = new StationOwner();
        stationOwner.setFullName(dto.getFullName());
        stationOwner.setEmail(dto.getEmail());
        stationOwner.setUsername(dto.getUsername());
        stationOwner.setPassword(dto.getPassword());
        stationOwner.setIdCardNumber(dto.getIdCardNumber());
        stationOwner.setPhoneNumber(dto.getPhoneNumber());
        //stationOwner.setStationAddress(dto.getStationAddress());
        stationOwnerRepository.save(stationOwner);
    }

    // Save Admin
    public void saveAdmin(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setFullName(dto.getFullName());
        admin.setEmail(dto.getEmail());
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());
        adminRepository.save(admin);
    }

    // Load User by Username (for JWT Authentication)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Search for the user in VehicleOwner repository
        Optional<VehicleOwner> vehicleOwner = vehicleOwnerRepository.findByUsername(username);
        if (vehicleOwner.isPresent()) {
            return vehicleOwner.get();
        }

        // Search for the user in StationOwner repository
        Optional<StationOwner> stationOwner = stationOwnerRepository.findByUsername(username);
        if (stationOwner.isPresent()) {
            return stationOwner.get();
        }

        // Search for the user in Admin repository
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        }

        // If user is not found in any repository, throw exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }


}
