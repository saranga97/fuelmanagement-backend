package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.LoginRequest;
import com.example.fuelmanagement.dto.RegisterRequest;
import com.example.fuelmanagement.model.VehicleOwner;
import com.example.fuelmanagement.repository.VehicleOwnerRepository;
import com.example.fuelmanagement.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("authService")
public class AuthService implements UserDetailsService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void register(RegisterRequest request) {
        VehicleOwner owner = new VehicleOwner();
        owner.setFullName(request.getFullName());
        owner.setEmail(request.getEmail());
        owner.setUsername(request.getUsername());
        owner.setPassword(passwordEncoder.encode(request.getPassword()));
        owner.setIdCardNumber(request.getIdCardNumber());
        owner.setPhoneNumber(request.getPhoneNumber());
        vehicleOwnerRepository.save(owner);
    }

    public String authenticateAndGenerateToken(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtUtils.generateJwtToken(request.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<VehicleOwner> owner = vehicleOwnerRepository.findByUsername(username);
        if (owner.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                owner.get().getUsername(),
                owner.get().getPassword(),
                new ArrayList<>()
        );
    }
}
