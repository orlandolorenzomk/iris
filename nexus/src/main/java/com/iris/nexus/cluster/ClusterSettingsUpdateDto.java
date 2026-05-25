package com.iris.nexus.cluster;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ClusterSettingsUpdateDto {

    @NotNull
    SdnZoneType sdnZoneType;

    @NotBlank
    String sdnZoneName;

    @NotBlank
    String natsUrl;
}
