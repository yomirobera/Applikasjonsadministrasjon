package com.example.applikasjonsadministrasjon.repositories;

import com.example.applikasjonsadministrasjon.models.Stilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StillingRepository extends JpaRepository<Stilling, Integer> {
}
