
package com.example.applikasjonsadministrasjon.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class MessagesNotFoundException extends RuntimeException {
    public MessagesNotFoundException(int id) {
        super(String.format("Messages with %d not found",id));
    }
}
