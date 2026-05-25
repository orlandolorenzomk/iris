package com.iris.nexus.auth.user.internal;

import com.iris.nexus.auth.user.Role;
import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class UserDto implements Serializable {
    UUID id;
    String email;
    Role role;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
}
