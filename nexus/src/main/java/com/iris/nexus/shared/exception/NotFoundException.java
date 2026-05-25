package com.iris.nexus.shared.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {

    public NotFoundException(String message, Object... args) {
        super(HttpStatus.NOT_FOUND, message, args);
    }
}
