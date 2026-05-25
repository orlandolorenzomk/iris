package com.iris.nexus.space.network;

import com.iris.nexus.space.network.internal.SpaceNetworkDto;

import java.util.List;
import java.util.UUID;

public interface SpaceNetworkService {

    SpaceNetworkDto findById(UUID id);

    List<SpaceNetworkDto> findBySpaceId(UUID spaceId);

    void create(SpaceNetworkCreateDto dto);

    void updateStatus(UUID id, SpaceNetworkStatus status);

    void delete(UUID id);
}
