package com.example.applikasjonsadministrasjon.models.dto.messages;

import lombok.Data;

@Data
public class MessagesPostDTO {
    private int id;

    
    private int chatSessionId;

    
    private String userId;

    private String message;



    private String timeStamp;
}
