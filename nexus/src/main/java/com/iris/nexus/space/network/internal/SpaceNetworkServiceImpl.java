package com.iris.nexus.space.network.internal;

import com.iris.nexus.shared.exception.NotFoundException;
import com.iris.nexus.space.internal.Space;
import com.iris.nexus.space.internal.SpaceRepository;
import com.iris.nexus.space.network.SpaceNetworkCreateDto;
import com.iris.nexus.space.network.SpaceNetworkService;
import com.iris.nexus.space.network.SpaceNetworkStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceNetworkServiceImpl implements SpaceNetworkService {

    private final SpaceNetworkRepository spaceNetworkRepository;
    private final SpaceNetworkMapper spaceNetworkMapper;
    private final SpaceRepository spaceRepository;

    @Transactional(readOnly = true)
    @Override
    public SpaceNetworkDto findById(UUID id) {
        return spaceNetworkRepository.findById(id)
                .map(spaceNetworkMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Space network with id {} does not exist", id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpaceNetworkDto> findBySpaceId(UUID spaceId) {
        return spaceNetworkRepository.findBySpaceId(spaceId)
                .stream()
                .map(spaceNetworkMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void create(SpaceNetworkCreateDto dto) {
        Space space = spaceRepository.findById(dto.getSpaceId())
                .orElseThrow(() -> new NotFoundException("Space with id {} does not exist", dto.getSpaceId()));

        SpaceNetwork network = spaceNetworkMapper.toEntity(dto);
        network.setSpace(space);
        spaceNetworkRepository.save(network);

        log.info("Created space network '{}' for space {}", dto.getVnetId(), dto.getSpaceId());
    }

    @Transactional
    @Override
    public void updateStatus(UUID id, SpaceNetworkStatus status) {
        SpaceNetwork network = spaceNetworkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Space network with id {} does not exist", id));

        network.setStatus(status);
        spaceNetworkRepository.save(network);

        log.info("Updated space network {} status to {}", id, status);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        SpaceNetwork network = spaceNetworkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Space network with id {} does not exist", id));

        spaceNetworkRepository.delete(network);
        log.info("Deleted space network {}", id);
    }
}
