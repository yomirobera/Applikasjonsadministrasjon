package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.tables.Stilling;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.models.dto.user.UserDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.user.UserUpdateDTO;
import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.models.tables.Stilling;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.repositories.MessagesRepository;
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

    @Autowired
    MessagesRepository messagesRepository;

    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "stillingToIds")
    //@Mapping(target = "madePositions", source= "stilling", qualifiedByName = "stillingToIds")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "stillingToIds")
    @Mapping(target = "senderUser", source = "userMessages", qualifiedByName = "messagesToIds")
    public abstract UserDTO userToUserDto(User user);


    public abstract Collection<UserDTO> userToUserDto(Collection<User> users);

    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToStilling")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "idsToStilling")
    @Mapping(target = "userMessages", source = "senderUser", qualifiedByName = "idsToMessages")
    public abstract User userUpdateDtoToUser(UserUpdateDTO userDTO);


    @Mapping(target = "stilling", source = "stilling", qualifiedByName = "idsToStilling")
    @Mapping(target = "madePositions", source = "madePositions", qualifiedByName = "idsToStilling")
    @Mapping(target = "userMessages", source = "senderUser", qualifiedByName = "idsToMessages")
    
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

    @AfterMapping
    protected void updateUserMessagesField(Set<String> users, @MappingTarget Set<User> userSet) {
        if (users != null && userSet != null) {
            userSet.forEach(user -> user.setUserMessages(null));
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

    @Named("idsToMessages")
    Set<Messages> mapToMessages(Set<Integer> source) {
        if (source == null) return null;
        return source.stream().map(id -> messagesRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("messagesToIds")
    Set<Integer> mapMessagesToIds(Set<Messages> source) {
        if (source == null) return null;
        return source.stream().map(x -> x.getId()
                        )
                .collect(Collectors.toSet());
    }
    
}











