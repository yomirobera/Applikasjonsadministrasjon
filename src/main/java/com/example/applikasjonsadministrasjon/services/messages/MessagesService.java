package com.example.applikasjonsadministrasjon.services.messages;

import java.util.Collection;

import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.services.CrudService;

public interface MessagesService extends CrudService<Messages, Integer> {
    
    public Collection<Messages> findAllBySessId(int sessID);
}
