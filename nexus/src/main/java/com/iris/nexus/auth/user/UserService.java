package com.iris.nexus.auth.user;

import com.iris.nexus.auth.user.internal.User;
import com.iris.nexus.auth.user.internal.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto findById(UUID id);

    UserDto findByEmail(String email);

    void create(UserCreateDto userCreate);

    void update(UserUpdateDto userUpdate);

    void delete(UUID id);

    User findEntityById(UUID id);
}
