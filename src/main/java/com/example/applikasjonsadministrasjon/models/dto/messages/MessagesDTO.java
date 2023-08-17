package com.example.applikasjonsadministrasjon.models.dto.messages;

import java.util.Set;

import com.example.applikasjonsadministrasjon.models.tables.ChatSession;
import com.example.applikasjonsadministrasjon.models.tables.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class MessagesDTO {
    
    
    
    private int id;

    
    private int chatSessionId;

    
    private String userId;

    private String message;



    private String timeStamp;
}
