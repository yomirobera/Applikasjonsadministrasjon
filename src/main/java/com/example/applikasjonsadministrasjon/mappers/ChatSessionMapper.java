package com.example.applikasjonsadministrasjon.mappers;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.applikasjonsadministrasjon.models.dto.chatSession.ChatSessionDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesUpdateDTO;
import com.example.applikasjonsadministrasjon.models.tables.ChatSession;
import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.repositories.ChatSessionRepository;
import com.example.applikasjonsadministrasjon.repositories.MessagesRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;

@Mapper(componentModel = "spring")
public abstract class ChatSessionMapper {

     @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatSessionRepository chatSessionRepository;

    @Mapping(target = "chatSessionMessages", source = "chatSessionMessages", qualifiedByName = "chatSessionToId")
    @Mapping(target = "participant1", source = "participant1", qualifiedByName = "userToId")
    @Mapping(target = "participant2", source = "participant2", qualifiedByName = "userToId")
    public abstract ChatSessionDTO chatSessionToChatSessionDTO(ChatSession chatSession);

    @Mapping(target = "chatSessionMessages", source = "chatSessionMessages", qualifiedByName = "idToChatSession")
    @Mapping(target = "participant1", source = "participant1", qualifiedByName = "idToUser")
    @Mapping(target = "participant2", source = "participant2", qualifiedByName = "idToUser")
    public abstract ChatSession chatSessionDTOToChatSession(ChatSessionDTO chatSessionDTO);

    public abstract Collection<ChatSessionDTO> messagesToMessagesDtos(Collection<ChatSession> chatSessions);

    

    @Named("idToChatSession")
    Set<Messages> mapIdToChatSession(Set<Integer> source) {
        if (source == null) return null;
        return source.stream().map(id -> messagesRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("chatSessionToId")
    Set<Integer> mapChatSessionToId (Set<Messages> source) {
        if (source == null) return null;
        return source.stream().map(message -> message.getId())
                .collect(Collectors.toSet());
    }

       @Named("userToId")
    String map(User source) {
        if (source == null) return null;
        return source.getId();
    }

    @Named("idToUser")
    User map(String source) {
        if (source == null) return null;
        return userRepository.findById(source)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + source));
    }

    
}
