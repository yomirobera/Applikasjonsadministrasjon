package com.example.applikasjonsadministrasjon.controllers;

import java.net.URI;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.applikasjonsadministrasjon.mappers.ChatSessionMapper;
import com.example.applikasjonsadministrasjon.models.dto.chatSession.ChatSessionDTO;
import com.example.applikasjonsadministrasjon.models.tables.ChatSession;

import com.example.applikasjonsadministrasjon.services.chatSession.ChatSessionService;


import com.example.applikasjonsadministrasjon.services.user.UserService;




@RestController
@RequestMapping(path = "api/v1/chatsession")
public class ChatSessionController {
    

    private final ChatSessionService chatSessionService;
    private final ChatSessionMapper chatSessionMapper;
    

    

    public ChatSessionController(ChatSessionService chatSessionService, ChatSessionMapper chatSessionMapper, UserService userService, ChatSessionMapper ChatSessionMapper) {
        this.chatSessionService = chatSessionService;
        this.chatSessionMapper = chatSessionMapper;
        
        
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(chatSessionMapper.messagesToMessagesDtos(
                chatSessionService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.ok(chatSessionMapper.chatSessionToChatSessionDTO(chatSessionService.findById(id)));
    }

    @GetMapping("{senderId}/{recipientId}")
    public ResponseEntity getChatSessionByParticipants(@PathVariable String senderId, @PathVariable String recipientId) {
        ChatSession session = chatSessionService.findByParticipants(senderId, recipientId);
        if (session != null) {
            return ResponseEntity.ok(chatSessionMapper.chatSessionToChatSessionDTO(session));
        } else {
            return ResponseEntity.notFound().build();
        }
}

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody ChatSessionDTO chatSessionObj, @PathVariable int id) {
        // Validates if body is correct
        if (id != chatSessionObj.getId())
            return ResponseEntity.badRequest().build();

        chatSessionService.update(chatSessionMapper.chatSessionDTOToChatSession(chatSessionObj));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ChatSessionDTO chatSessionObj) {
        ChatSession message = chatSessionService.add(chatSessionMapper.chatSessionDTOToChatSession(chatSessionObj));
        URI location = URI.create("chatsession/" + message.getId());
        return ResponseEntity.created(location).build();
  }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        chatSessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

  

   







}


