package com.example.applikasjonsadministrasjon.models.dto.chatSession;

import java.util.Set;

import lombok.Data;

@Data
public class ChatSessionDTO {
    
    private int id;

    private String chatName;

    private Set<Integer> chatSessionMessages;

    private String timeStamp;

    private String participant1;

    private String participant2;
}
