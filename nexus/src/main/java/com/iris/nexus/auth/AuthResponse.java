package com.iris.nexus.auth;

import com.iris.nexus.auth.user.Role;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AuthResponse(
        String token,
        UUID id,
        String email,
        Role role,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
