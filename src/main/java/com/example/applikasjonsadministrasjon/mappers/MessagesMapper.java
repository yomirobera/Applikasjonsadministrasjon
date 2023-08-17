package com.example.applikasjonsadministrasjon.mappers;


import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesPostDTO;
import com.example.applikasjonsadministrasjon.models.dto.messages.MessagesUpdateDTO;
import com.example.applikasjonsadministrasjon.models.tables.ChatSession;
import com.example.applikasjonsadministrasjon.models.tables.Messages;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.repositories.ChatSessionRepository;
import com.example.applikasjonsadministrasjon.repositories.MessagesRepository;
import com.example.applikasjonsadministrasjon.repositories.UserRepository;

import java.util.Collection;


@Mapper(componentModel = "spring")
public abstract class MessagesMapper {
    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatSessionRepository chatSessionRepository;

    @Mapping(target = "chatSessionId", source="chatSession", qualifiedByName = "chatSessionToId")
    @Mapping(target="userId", source= "senderUser", qualifiedByName = "userToId")
    public abstract MessagesDTO messageToMessagesDTO(Messages messages);

    public abstract Collection<MessagesDTO> messagesToMessagesDtos(Collection<Messages> messages);

    @Mapping(target = "chatSession", source = "chatSessionId", qualifiedByName = "idToChatSession")
    @Mapping(target ="senderUser", source = "userId", qualifiedByName="idToUser")
    public abstract Messages messagesUpdateDTOToMessages(MessagesUpdateDTO messagesUpdateDTO);

    @Mapping(target = "chatSession", source = "chatSessionId", qualifiedByName = "idToChatSession")
    @Mapping(target ="senderUser", source = "userId", qualifiedByName="idToUser")
    public abstract Messages messagesPostDTOToMessages(MessagesPostDTO messagesPostDTO);

    @Named("idToUser")
    User mapIdToUser(String source) {
        if (source == null) return null;
        return userRepository.findById(source)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + source));
    }

     @Named("idToChatSession")
    ChatSession mapIdToChatSession(Integer source) {
        if (source == null) return null;
        return chatSessionRepository.findById(source)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + source));
    }

    @Named("userToId")
    String mapUserToId(User user){
        if(user== null) return null;
        if (user.getId() == null) {
        throw new IllegalArgumentException("Can't find user: " + user);
        }
        return user.getId();
}

    @Named("chatSessionToId")
    Integer mapChatSessionToId (ChatSession chatSession) {
        if (chatSession == null) return null;
        return chatSession.getId();
    }
}