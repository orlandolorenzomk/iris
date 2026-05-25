package com.iris.nexus.machine.internal;

import com.iris.nexus.shared.exception.ConflictException;
import com.iris.nexus.shared.exception.NotFoundException;
import com.iris.nexus.shared.utils.Utils;
import com.iris.nexus.machine.MachineCreateDto;
import com.iris.nexus.machine.MachineService;
import com.iris.nexus.machine.MachineUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final MachineMapper machineMapper;

    @Transactional(readOnly = true)
    @Override
    public MachineDto findById(UUID id) {
        return machineRepository.findById(id)
                .map(machineMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Machine with id {} does not exist", id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MachineDto> findAll() {
        return machineRepository.findAll()
                .stream()
                .map(machineMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void create(MachineCreateDto dto) {
        String normalizedName = Utils.normalizeName(dto.getName());

        if (machineRepository.existsByName(normalizedName)) {
            throw new ConflictException("Machine with name '{}' already exists", normalizedName);
        }

        Machine machine = machineMapper.toEntity(dto);
        machine.setName(normalizedName);
        machineRepository.save(machine);

        log.info("Created machine '{}'", normalizedName);
    }

    @Transactional
    @Override
    public void update(MachineUpdateDto dto) {
        Machine machine = machineRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Machine with id {} does not exist", dto.getId()));

        if (dto.getName() != null) {
            String normalizedName = Utils.normalizeName(dto.getName());
            if (!normalizedName.equals(machine.getName()) && machineRepository.existsByName(normalizedName)) {
                throw new ConflictException("Machine with name '{}' already exists", normalizedName);
            }
            machine.setName(normalizedName);
        }

        machineMapper.partialUpdate(dto, machine);
        machineRepository.save(machine);

        log.info("Updated machine {}", dto.getId());
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Machine with id {} does not exist", id));

        machineRepository.delete(machine);
        log.info("Deleted machine {}", id);
    }
}
