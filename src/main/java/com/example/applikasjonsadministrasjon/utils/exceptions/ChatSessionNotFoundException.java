package com.example.applikasjonsadministrasjon.utils.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ChatSessionNotFoundException extends RuntimeException {
    public ChatSessionNotFoundException(int id) {
        super(String.format("ChatSession with %d not found",id));
    }
}
