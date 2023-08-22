package com.example.applikasjonsadministrasjon.services.chatSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.applikasjonsadministrasjon.models.tables.ChatSession;
import com.example.applikasjonsadministrasjon.repositories.ChatSessionRepository;
import com.example.applikasjonsadministrasjon.utils.exceptions.ChatSessionExists;
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
        Optional<ChatSession> check= chatSessionRepository.findByParticipants(entity.getParticipant1().getId(), entity.getParticipant2().getId());
        if(check.isPresent()){
            return check.get();
            
        }else{
        ChatSession savedStilling = chatSessionRepository.save(entity);
       
        return savedStilling;}
    }
/* 
    @Override
    public ChatSession findByParticipants(String part1, String part2){
        ArrayList<ChatSession> all = (ArrayList)chatSessionRepository.findAll();
            for (ChatSession a : all) {
                if((a.getParticipant1().getId().equals(part1) || a.getParticipant1().getId().equals(part2)) && (a.getParticipant2().getId().equals( part1)) || (a.getParticipant2().getId().equals( part2)) ){
                    return a;
                }
            }
            return null;
        } */

        public ChatSession findByParticipants(String part1, String part2) {
            return chatSessionRepository.findByParticipants(part1, part2).orElse(null);
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