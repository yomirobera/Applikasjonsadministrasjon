package com.example.applikasjonsadministrasjon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.applikasjonsadministrasjon.models.tables.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    
}
