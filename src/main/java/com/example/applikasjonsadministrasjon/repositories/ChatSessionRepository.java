package com.example.applikasjonsadministrasjon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.applikasjonsadministrasjon.models.tables.ChatSession;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer>{

      @Query("SELECT cs FROM ChatSession cs WHERE (cs.participant1.id = ?1 AND cs.participant2.id = ?2) OR (cs.participant1.id = ?2 AND cs.participant2.id = ?1)")
    Optional<ChatSession> findByParticipants(String part1, String part2);
}
    

