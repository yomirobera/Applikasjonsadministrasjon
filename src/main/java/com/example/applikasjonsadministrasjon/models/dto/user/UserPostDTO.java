package com.example.applikasjonsadministrasjon.models.dto.user;

import com.example.applikasjonsadministrasjon.models.Stilling;
import lombok.Data;

import java.util.Set;

@Data
public class UserPostDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean selger;

    //private Set<Integer> stilling;
}
