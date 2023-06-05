package com.example.applikasjonsadministrasjon.models.dto.stilling;

import com.example.applikasjonsadministrasjon.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class StillingDTO {

    private int id;

    private String tittel;

    private String beskrivelse;

    private String krav;

    private String plassering;

    private Date soknadsfrist;

    private String PDF;

    private Set<String> users;
}
