package com.example.fuelmanagement.service;

import com.example.fuelmanagement.dto.UserDTO;
import com.example.fuelmanagement.mapper.UserMapper;
import com.example.fuelmanagement.model.User;
import com.example.fuelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }
}
