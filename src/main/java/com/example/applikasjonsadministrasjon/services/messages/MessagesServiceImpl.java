package com.example.applikasjonsadministrasjon.services.messages;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.models.tables.Stilling;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.repositories.MessagesRepository;
import com.example.applikasjonsadministrasjon.repositories.StillingRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;
import com.example.applikasjonsadministrasjon.utils.exceptions.MessagesNotFoundException;
import com.example.applikasjonsadministrasjon.utils.exceptions.StillingNotFoundException;
import com.example.applikasjonsadministrasjon.utils.exceptions.UserNotFoundException;

@Service
public class MessagesServiceImpl implements MessagesService {

private final MessagesRepository messagesRepository;
    
public MessagesServiceImpl(MessagesRepository messagesRepository){
this.messagesRepository=messagesRepository;
}
  


   @Override
    public Messages findById(Integer id) {
        return messagesRepository.findById(id).orElseThrow(() -> new MessagesNotFoundException(id));
    }

    @Override
    public Collection<Messages> findAll() {
        return messagesRepository.findAll();
    }

    @Override
    public Collection<Messages> findAllBySessId(int sessId) {
        return messagesRepository.findAllBySessId(sessId);
    }


    @Override
    public Messages add(Messages entity) {
        Messages savedStilling = messagesRepository.save(entity);
       
        return savedStilling;
    }


    public void update(Messages entity) {
        //


        messagesRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        
        
       
        messagesRepository.deleteById(id);
    }
    
}
