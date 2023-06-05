package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "stillingToIds")
    public abstract UserDTO userToUserDto(User user);
    public abstract Collection<UserDTO> userToUserDto(Collection<User> users);

    public abstract User userUpdateDtoToUser(UserUpdateDTO userDTO);

    public abstract User userPostDtoToUser(UserPostDTO userDto);

    @Named("stillingToIds")
    Set<Integer> mapStilling(Set<Stilling> source) {
        if (source == null) return null;
        return source.stream().map(p -> p.getId()
        ).collect(Collectors.toSet());
    }


}
