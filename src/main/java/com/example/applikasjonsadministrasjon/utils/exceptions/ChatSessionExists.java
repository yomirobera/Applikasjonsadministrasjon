package com.example.applikasjonsadministrasjon.utils.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;


public class ChatSessionExists extends RuntimeException {
    public ChatSessionExists(int id) {
        super(String.format("ChatSession with %d already exists",id));
}
}
