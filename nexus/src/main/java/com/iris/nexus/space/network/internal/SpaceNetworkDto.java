package com.iris.nexus.space.network.internal;

import com.iris.nexus.space.network.SpaceNetworkStatus;
import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class SpaceNetworkDto implements Serializable {
    UUID id;
    UUID spaceId;
    String vnetId;
    String subnet;
    Integer vni;
    SpaceNetworkStatus status;
    OffsetDateTime createdAt;
}
