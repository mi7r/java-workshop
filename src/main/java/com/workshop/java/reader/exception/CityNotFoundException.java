package com.workshop.java.reader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "INVALID_CITY_NAME")
public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super();
    }
}
