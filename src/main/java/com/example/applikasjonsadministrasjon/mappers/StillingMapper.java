package com.example.applikasjonsadministrasjon.mappers;

import com.example.applikasjonsadministrasjon.models.Stilling;
import com.example.applikasjonsadministrasjon.models.User;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingUpdateDTO;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class StillingMapper {
    @Autowired
    UserRepository userRepository;

    @Mapping(target = "users", source = "users", qualifiedByName = "usersToIds")
    public abstract StillingDTO stillingToStillingDTO(Stilling stilling);

    public abstract Collection<StillingDTO> stillingToStillingDTO(Collection<Stilling> stillinger);

    @Mapping(target = "users", source = "users", qualifiedByName = "idsToUsers")
    public abstract Stilling stillingUpdateDtoToStilling(StillingUpdateDTO projectDTO);

    @Mapping(target = "users", source = "users", qualifiedByName = "idsToUsers")
    public abstract Stilling stillingPostDtoToStilling(StillingPostDTO stillingDto);

    @Named("usersToIds")
    Set<String> map(Set<User> source) {
        if (source == null) return null;
        return source.stream().map(p -> p.getId()
        ).collect(Collectors.toSet());
    }
    @Named("idsToUsers")
    Set<User> mapToUser(Set<String> source) {
        if (source == null) return null;
        return source.stream().map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)))
                .collect(Collectors.toSet());
    }


}
