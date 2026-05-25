package com.iris.nexus.space;

import com.iris.nexus.space.internal.SpaceDto;

import java.util.List;
import java.util.UUID;

public interface SpaceService {

    SpaceDto findById(UUID id);

    List<SpaceDto> findByUserId(UUID userId);

    void create(SpaceCreateDto dto);

    void update(SpaceUpdateDto dto);

    void delete(UUID id);
}
