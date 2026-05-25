package com.iris.nexus.cluster;

import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class ClusterSettingsDto {
    SdnZoneType sdnZoneType;
    String sdnZoneName;
    String natsUrl;
    OffsetDateTime updatedAt;
}
