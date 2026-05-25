package com.iris.nexus.space.internal;

import com.iris.nexus.auth.user.internal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpaceRepository extends JpaRepository<Space, UUID> {

    List<Space> findByUserId(UUID userId);

    List<Space> findAllByUser(User user);

    boolean existsByName(String name);
}
