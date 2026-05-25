package com.iris.nexus.space;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class SpaceUpdateDto {

    @NotNull
    UUID id;

    String name;

    @Min(1)
    Integer cpuLimit;

    @Min(1)
    Integer ramLimit;

    @Min(1)
    Long storageLimit;
}
