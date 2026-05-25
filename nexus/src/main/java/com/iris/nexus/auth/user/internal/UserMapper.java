package com.iris.nexus.auth.user.internal;

import com.iris.nexus.auth.user.Role;
import com.iris.nexus.auth.user.UserCreateDto;
import com.iris.nexus.auth.user.UserUpdateDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        imports = Role.class)
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "role", expression = "java(Role.USER)")
    User toEntity(UserCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDto dto, @MappingTarget User user);
}