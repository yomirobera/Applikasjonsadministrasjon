package com.example.applikasjonsadministrasjon.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "stilling")
public class Stilling {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private int id;

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

    @ManyToMany(mappedBy = "stilling")
    private Set<User> users;

}
