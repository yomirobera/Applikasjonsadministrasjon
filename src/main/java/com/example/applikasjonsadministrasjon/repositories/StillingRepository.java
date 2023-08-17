package com.example.applikasjonsadministrasjon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.applikasjonsadministrasjon.models.tables.Stilling;

public interface StillingRepository extends JpaRepository<Stilling, Integer> {
}
