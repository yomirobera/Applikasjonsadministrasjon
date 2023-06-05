package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDTO userToUserDto(User user);
    public abstract Collection<UserDTO> userToUserDto(Collection<User> users);

    public abstract User userUpdateDtoToUser(UserUpdateDTO userDTO);

    public abstract User userPostDtoToUser(UserPostDTO userDto);

}
