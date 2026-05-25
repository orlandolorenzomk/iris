package com.iris.nexus.space;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class SpaceCreateDto {

    @NotNull
    UUID userId;

    @NotBlank
    String name;

    @NotNull
    @Min(1)
    Integer cpuLimit;

    @NotNull
    @Min(1)
    Integer ramLimit;

    @NotNull
    @Min(1)
    Long storageLimit;
}
