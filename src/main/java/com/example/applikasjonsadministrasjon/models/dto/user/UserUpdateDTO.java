package com.example.applikasjonsadministrasjon.models.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean selger;

    private Set<Integer> stilling;

}
