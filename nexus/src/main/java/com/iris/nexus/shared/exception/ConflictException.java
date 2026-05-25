package com.iris.nexus.shared.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends AppException {

    public ConflictException(String message, Object... args) {
        super(HttpStatus.CONFLICT, message, args);
    }
}
