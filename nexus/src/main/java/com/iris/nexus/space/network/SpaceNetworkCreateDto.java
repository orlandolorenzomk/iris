package com.iris.nexus.space.network;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class SpaceNetworkCreateDto {

    @NotNull
    UUID spaceId;

    @NotBlank
    String vnetId;

    @NotBlank
    String subnet;

    Integer vni;
}
