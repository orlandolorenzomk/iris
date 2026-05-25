package com.iris.nexus.machine;

import com.iris.nexus.machine.internal.MachineDto;

import java.util.List;
import java.util.UUID;

public interface MachineService {

    MachineDto findById(UUID id);

    List<MachineDto> findAll();

    void create(MachineCreateDto dto);

    void update(MachineUpdateDto dto);

    void delete(UUID id);
}
