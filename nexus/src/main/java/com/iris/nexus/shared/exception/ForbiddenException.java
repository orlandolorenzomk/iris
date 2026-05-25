package com.iris.nexus.shared.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AppException {

    public ForbiddenException(String message, Object... args) {
        super(HttpStatus.FORBIDDEN, message, args);
    }
}
