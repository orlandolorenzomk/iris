package com.iris.nexus.auth.user.internal;

import com.iris.nexus.auth.user.Role;
import com.iris.nexus.auth.user.UserCreateDto;
import com.iris.nexus.auth.user.UserService;
import com.iris.nexus.auth.user.UserUpdateDto;
import com.iris.nexus.shared.exception.ConflictException;
import com.iris.nexus.shared.exception.ForbiddenException;
import com.iris.nexus.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User with id {} was not found", id));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User with email {} was not found", email));
    }

    @Transactional
    @Override
    public void create(UserCreateDto userCreate) {
        if (userRepository.existsByEmail(userCreate.getEmail())) {
            throw new ConflictException("User with email {} already exists", userCreate.getEmail());
        }

        User user = userMapper.toEntity(userCreate);
        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        userRepository.save(user);

        log.info("Created user with email {}", userCreate.getEmail());
    }

    @Transactional
    @Override
    public void update(UserUpdateDto userUpdate) {
        User user = userRepository.findById(userUpdate.getId())
                .orElseThrow(() -> new NotFoundException("User with id {} was not found", userUpdate.getId()));

        if (userUpdate.getEmail() != null && !userUpdate.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(userUpdate.getEmail())) {
            throw new ConflictException("User with email {} already exists", userUpdate.getEmail());
        }

        userMapper.partialUpdate(userUpdate, user);

        if (userUpdate.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        }

        userRepository.save(user);

        log.info("Updated user with id {}", userUpdate.getId());
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id {} was not found", id));

        if (user.getRole() == Role.OWNER) {
            throw new ForbiddenException("The owner account cannot be deleted");
        }

        userRepository.delete(user);
        log.info("Deleted user with id {}", id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id {} was not found", id));
    }
}
