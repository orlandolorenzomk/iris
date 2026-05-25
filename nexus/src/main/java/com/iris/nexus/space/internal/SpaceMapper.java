package com.iris.nexus.space.internal;

import com.iris.nexus.space.SpaceCreateDto;
import com.iris.nexus.space.SpaceUpdateDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpaceMapper {

    @Mapping(target = "userId", source = "user.id")
    SpaceDto toDto(Space space);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "user", ignore = true)
    Space toEntity(SpaceCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", ignore = true)
    Space partialUpdate(SpaceUpdateDto dto, @MappingTarget Space space);
}
