package com.iris.nexus.auth.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    java.util.Optional<User> findByEmail(String email);
}