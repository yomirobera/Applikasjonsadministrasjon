package com.example.applikasjonsadministrasjon.models.dto.stilling;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class StillingPostDTO {
    private int id;

    private String tittel;

    private String beskrivelse;

    private String krav;

    private String plassering;

    private Date soknadsfrist;

    private String PDF;

    private Set<String> users;
}
