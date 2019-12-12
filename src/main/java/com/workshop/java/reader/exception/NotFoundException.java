package com.workshop.java.reader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
