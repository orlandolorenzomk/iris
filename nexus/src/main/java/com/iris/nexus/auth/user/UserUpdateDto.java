package com.iris.nexus.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class UserUpdateDto {

    @NotNull
    UUID id;

    @Email
    @Size(max = 255)
    String email;

    @Size(min = 8, max = 30)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    String password;
}
