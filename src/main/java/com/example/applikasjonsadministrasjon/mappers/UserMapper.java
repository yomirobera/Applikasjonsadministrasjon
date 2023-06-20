package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import com.example.applikasjonsadministrasjon.repositories.StillingRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    StillingRepository stillingRepository;
    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "stillingToIds")
    public abstract UserDTO userToUserDto(User user);


    public abstract Collection<UserDTO> userToUserDto(Collection<User> users);

    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToStilling")

    public abstract User userUpdateDtoToUser(UserUpdateDTO userDTO);


    //@Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToUsers")
    public abstract User userPostDtoToUser(UserPostDTO userDto);

    @Named("stillingToIds")
    Set<Integer> mapStilling(Set<Stilling> source) {
        if (source == null) return null;
        return source.stream().map(p -> p.getId()
        ).collect(Collectors.toSet());
    }

    @Named("idsToStilling")
    Set<Stilling> mapToUser(Set<Integer> source) {
        if (source == null) return null;
        return source.stream().map(id -> stillingRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)))
                .collect(Collectors.toSet());
    }

}
