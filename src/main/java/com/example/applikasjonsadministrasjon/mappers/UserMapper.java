package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import com.example.applikasjonsadministrasjon.repositories.StillingRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    StillingRepository stillingRepository;

   @Autowired
    UserRepository userRepository;



    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "stillingToIds")
    //@Mapping(target = "madePositions", source= "stilling", qualifiedByName = "stillingToIds")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "stillingToIds")
    public abstract UserDTO userToUserDto(User user);


    public abstract Collection<UserDTO> userToUserDto(Collection<User> users);

    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToStilling")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "idsToStilling")
    public abstract User userUpdateDtoToUser(UserUpdateDTO userDTO);


    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToStilling")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "idsToStilling")
    public abstract User userPostDtoToUser(UserPostDTO userDto);






    @Mapping(source = "users", target ="users" , qualifiedByName = "stringsToUsers")
    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "stillingToIds")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "stillingToIds")
    public abstract Set<User> stringsUsersToUsers(Set<String> users);

    @AfterMapping
    protected void updateStillingField(Set<String> users, @MappingTarget Set<User> userSet) {
        if (users != null && userSet != null) {
            userSet.forEach(user -> user.setStilling(null));
        }
    }

    @AfterMapping
    protected void updateMadePositionField(Set<String> users, @MappingTarget Set<User> userSet) {
        if (users != null && userSet != null) {
            userSet.forEach(user -> user.setMadePositions(null));
        }
    }

    @Named("stillingToIds")
    Set<Integer> mapStilling(Set<Stilling> source) {
        if (source == null) return null;
        return source.stream().map(Stilling::getId)
        .collect(Collectors.toSet());
    }




    @Named("idsToStilling")
    Set<Stilling> mapToUser(Set<Integer> source) {
        if (source == null) return null;
        return source.stream().map(id -> stillingRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)))
                .collect(Collectors.toSet());
    }


    @Named("stringsToUsers")
    Set<User> stringsToUsers(Set<String> users) {
        if (users == null) return null;
        return users.stream()
                .map(this::findUserById)
                .collect(Collectors.toSet());
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
    }
}











