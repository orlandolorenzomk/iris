package com.iris.nexus.machine.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MachineRepository extends JpaRepository<Machine, UUID> {

    boolean existsByName(String name);
}
