package com.iris.nexus.cluster.internal;

import com.iris.nexus.cluster.ClusterSettingsDto;
import com.iris.nexus.cluster.ClusterSettingsUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClusterSettingsMapper {

    ClusterSettingsDto toDto(ClusterSettings settings);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClusterSettings partialUpdate(ClusterSettingsUpdateDto dto, @MappingTarget ClusterSettings settings);
}
