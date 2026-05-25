package com.iris.nexus.shared.exception;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(HttpStatus status, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
        this.status = status;
    }
}
