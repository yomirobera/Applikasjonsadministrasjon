package com.example.applikasjonsadministrasjon.models.dto.user;

import lombok.Data;

import java.util.Set;

import com.example.applikasjonsadministrasjon.models.tables.Stilling;

@Data
public class UserPostDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean selger;

    private Set<Integer> madePositions;

    private Set<Integer> stilling;
    private Set<Integer> senderUser;
}
