package com.example.applikasjonsadministrasjon.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Date;

public class stilling {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String id;

    @Column(length = 50,nullable = false)
    private String tittel;
    @Column(length = 50,nullable = false)
    private String beskrivelse;
    @Column(length = 100,nullable = false)
    private String krav;

    @Column(length = 50,nullable = false)
    private String plassering;

    @Column(length = 50,nullable = false)
    private Date soknadsfrist;

    @Column(length = 50,nullable = false)
    private String PDF;


}
