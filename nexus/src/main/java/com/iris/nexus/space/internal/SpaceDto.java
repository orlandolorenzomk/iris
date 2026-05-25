package com.iris.nexus.space.internal;

import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class SpaceDto implements Serializable {
    UUID id;
    UUID userId;
    String name;
    Integer cpuLimit;
    Integer ramLimit;
    Long storageLimit;
    OffsetDateTime createdAt;
}
