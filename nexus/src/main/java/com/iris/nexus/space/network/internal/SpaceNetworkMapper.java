package com.iris.nexus.space.network.internal;

import com.iris.nexus.space.network.SpaceNetworkCreateDto;
import com.iris.nexus.space.network.SpaceNetworkStatus;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        imports = SpaceNetworkStatus.class)
public interface SpaceNetworkMapper {

    @Mapping(target = "spaceId", source = "space.id")
    SpaceNetworkDto toDto(SpaceNetwork spaceNetwork);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(SpaceNetworkStatus.PENDING_APPLY)")
    @Mapping(target = "space", ignore = true)
    SpaceNetwork toEntity(SpaceNetworkCreateDto dto);
}
