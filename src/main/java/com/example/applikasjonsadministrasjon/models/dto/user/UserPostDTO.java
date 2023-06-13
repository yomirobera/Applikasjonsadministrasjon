package com.example.applikasjonsadministrasjon.models.dto.user;

import lombok.Data;

@Data
public class UserPostDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean selger;
}
