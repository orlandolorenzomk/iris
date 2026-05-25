package com.iris.nexus.shared.exception;

import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors
) {
    static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, message, null);
    }
}
