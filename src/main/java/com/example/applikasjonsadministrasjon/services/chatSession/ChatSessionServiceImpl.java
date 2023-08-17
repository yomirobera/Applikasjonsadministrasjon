package com.example.applikasjonsadministrasjon.services.chatSession;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.applikasjonsadministrasjon.models.tables.ChatSession;
import com.example.applikasjonsadministrasjon.repositories.ChatSessionRepository;
import com.example.applikasjonsadministrasjon.utils.exceptions.ChatSessionNotFoundException;
import com.example.applikasjonsadministrasjon.utils.exceptions.StillingNotFoundException;

@Service
public class ChatSessionServiceImpl implements ChatSessionService{

private final ChatSessionRepository chatSessionRepository;
    
public ChatSessionServiceImpl(ChatSessionRepository chatSessionRepository){
this.chatSessionRepository=chatSessionRepository;
}
  


   @Override
    public ChatSession findById(Integer id) {
        return chatSessionRepository.findById(id).orElseThrow(() -> new ChatSessionNotFoundException(id));
    }

    @Override
    public Collection<ChatSession> findAll() {
        return chatSessionRepository.findAll();
    }


    @Override
    public ChatSession add(ChatSession entity) {
        ChatSession savedStilling = chatSessionRepository.save(entity);
       
        return savedStilling;
    }


    public void update(ChatSession entity) {
        //


        chatSessionRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        
        
       
        chatSessionRepository.deleteById(id);
    }
}