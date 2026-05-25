package com.iris.nexus.space.internal;

import com.iris.nexus.auth.user.UserService;
import com.iris.nexus.auth.user.internal.User;
import com.iris.nexus.shared.exception.ConflictException;
import com.iris.nexus.shared.exception.NotFoundException;
import com.iris.nexus.shared.utils.Utils;
import com.iris.nexus.space.SpaceCreateDto;
import com.iris.nexus.space.SpaceService;
import com.iris.nexus.space.SpaceUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;

    private final SpaceMapper spaceMapper;

    private final UserService userService;

    @Transactional(readOnly = true)
    @Override
    public SpaceDto findById(UUID id) {
        return spaceRepository.findById(id)
                .map(spaceMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Space with id {} does not exist", id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpaceDto> findByUserId(UUID userId) {
        User user = userService.findEntityById(userId);
        return spaceRepository.findAllByUser(user)
                .stream()
                .map(spaceMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void create(SpaceCreateDto dto) {
        User user = userService.findEntityById(dto.getUserId());

        String normalizedName = Utils.normalizeName(dto.getName());
        if (spaceRepository.existsByName(normalizedName)) {
            throw new ConflictException("Space with name {} already exists", normalizedName);
        }

        Space space = spaceMapper.toEntity(dto);
        space.setUser(user);
        space.setName(normalizedName);
        spaceRepository.save(space);

        log.info("Created space '{}' for user {}", dto.getName(), dto.getUserId());
    }

    @Transactional
    @Override
    public void update(SpaceUpdateDto dto) {
        Space space = spaceRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Space with id {} does not exist", dto.getId()));

        spaceMapper.partialUpdate(dto, space);
        spaceRepository.save(space);

        log.info("Updated space {}", dto.getId());
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Space with id {} does not exist", id));

        spaceRepository.delete(space);
        log.info("Deleted space {}", id);
    }
}
