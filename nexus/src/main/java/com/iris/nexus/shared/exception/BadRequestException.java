package com.iris.nexus.shared.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AppException {

    public BadRequestException(String message, Object... args) {
        super(HttpStatus.BAD_REQUEST, message, args);
    }
}
