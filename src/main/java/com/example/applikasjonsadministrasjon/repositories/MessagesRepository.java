package com.example.applikasjonsadministrasjon.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.applikasjonsadministrasjon.models.tables.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    @Query("SELECT m FROM Messages m WHERE m.chatSession.id = ?1")
    Collection<Messages> findAllBySessId(int sessID); 
    
}
