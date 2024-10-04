package com.example.fuelmanagement.mapper;

import com.example.fuelmanagement.dto.UserDTO;
import com.example.fuelmanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", source = "password")
    UserDTO userToUserDTO(User user);

    @Mapping(target = "password", source = "password")
    User userDTOToUser(UserDTO userDTO);
}
