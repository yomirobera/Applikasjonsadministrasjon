package com.example.applikasjonsadministrasjon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.applikasjonsadministrasjon.models.tables.ChatSession;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer>{
    
}
