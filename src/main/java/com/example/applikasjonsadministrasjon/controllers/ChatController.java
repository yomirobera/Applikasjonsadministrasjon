package com.example.applikasjonsadministrasjon.controllers;

import com.example.applikasjonsadministrasjon.models.ChatMessage;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin("*")
@Controller
public class ChatController {

   
     @MessageMapping("/chat.sendTo/{userId}")
    public void sendPrivateMessage(@DestinationVariable String userId, @Payload ChatMessage chatMessage) {
        System.out.println("Sending to user: " + userId);
        System.out.println(chatMessage);
        simpMessagingTemplate.convertAndSend( "/queue/messages", chatMessage);
    }


private final SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
}