package com.example.applikasjonsadministrasjon.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ChatMessage{
    private String senderId;
    private String message;
    private String timeStamp;
}