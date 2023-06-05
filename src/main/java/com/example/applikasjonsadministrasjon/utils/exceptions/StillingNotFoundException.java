package com.example.applikasjonsadministrasjon.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class StillingNotFoundException extends RuntimeException {
    public StillingNotFoundException(int id) {
        super(String.format("Stilling with %d not found",id));
    }
}
