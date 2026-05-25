package com.iris.nexus.shared.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AppException {

    public UnauthorizedException(String message, Object... args) {
        super(HttpStatus.UNAUTHORIZED, message, args);
    }
}
