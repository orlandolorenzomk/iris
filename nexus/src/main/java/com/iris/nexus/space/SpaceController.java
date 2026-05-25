package com.iris.nexus.space;

import com.iris.nexus.shared.constants.UrlMapping;
import com.iris.nexus.space.internal.SpaceDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(UrlMapping.SPACES_BASE_URL)
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/{id}")
    public ResponseEntity<SpaceDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(spaceService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SpaceDto>> findByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(spaceService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SpaceCreateDto dto) {
        spaceService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody SpaceUpdateDto dto) {
        spaceService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        spaceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
