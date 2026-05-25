package com.iris.nexus.space.network;

import com.iris.nexus.shared.constants.UrlMapping;
import com.iris.nexus.space.network.internal.SpaceNetworkDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(UrlMapping.SPACE_NETWORKS_BASE_URL)
@RequiredArgsConstructor
public class SpaceNetworkController {

    private final SpaceNetworkService spaceNetworkService;

    @GetMapping("/{id}")
    public ResponseEntity<SpaceNetworkDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(spaceNetworkService.findById(id));
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<SpaceNetworkDto>> findBySpaceId(@PathVariable UUID spaceId) {
        return ResponseEntity.ok(spaceNetworkService.findBySpaceId(spaceId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SpaceNetworkCreateDto dto) {
        spaceNetworkService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam SpaceNetworkStatus status) {
        spaceNetworkService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        spaceNetworkService.delete(id);
        return ResponseEntity.ok().build();
    }
}
