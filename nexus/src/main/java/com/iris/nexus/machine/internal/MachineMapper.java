package com.iris.nexus.machine.internal;

import com.iris.nexus.machine.MachineCreateDto;
import com.iris.nexus.machine.MachineStatus;
import com.iris.nexus.machine.MachineUpdateDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        imports = MachineStatus.class)
public interface MachineMapper {

    MachineDto toDto(Machine machine);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(MachineStatus.BOOTSTRAPPING)")
    @Mapping(target = "isPrimary", constant = "false")
    Machine toEntity(MachineCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Machine partialUpdate(MachineUpdateDto dto, @MappingTarget Machine machine);
}
