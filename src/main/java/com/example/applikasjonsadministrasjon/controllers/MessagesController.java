package com.example.applikasjonsadministrasjon.controllers;

import java.net.URI;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.applikasjonsadministrasjon.mappers.MessagesMapper;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesUpdateDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.stilling.StillingUpdateDTO;
import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.models.tables.Stilling;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.services.messages.MessagesService;
import com.example.applikasjonsadministrasjon.services.stilling.StillingService;
import com.example.applikasjonsadministrasjon.services.user.UserService;




@RestController
@RequestMapping(path = "api/v1/messages")
public class MessagesController {
    

    private final MessagesService messagesService;
    private final MessagesMapper messagesMapper;
    

    

    public MessagesController(MessagesService messagesService, MessagesMapper messagesMapper, UserService userService, MessagesMapper MessagesMapper) {
        this.messagesService = messagesService;
        this.messagesMapper = messagesMapper;
        
        
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(messagesMapper.messagesToMessagesDtos(
                messagesService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.ok(messagesMapper.messageToMessagesDTO(messagesService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody MessagesUpdateDTO messagesUpdateDTO, @PathVariable int id) {
        // Validates if body is correct
        if (id != messagesUpdateDTO.getId())
            return ResponseEntity.badRequest().build();

        messagesService.update(messagesMapper.messagesUpdateDTOToMessages(messagesUpdateDTO));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity add(@RequestBody MessagesPostDTO messageDto) {
        Messages message = messagesService.add(messagesMapper.messagesPostDTOToMessages(messageDto));
        URI location = URI.create("messages/" + message.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        messagesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

  

   







}


