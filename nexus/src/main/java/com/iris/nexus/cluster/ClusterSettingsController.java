package com.iris.nexus.cluster;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cluster-settings")
@RequiredArgsConstructor
public class ClusterSettingsController {

    private final ClusterSettingsService clusterSettingsService;

    @GetMapping
    public ResponseEntity<ClusterSettingsDto> getSettings() {
        return ResponseEntity.ok(clusterSettingsService.getSettings());
    }

    @PatchMapping
    public ResponseEntity<ClusterSettingsDto> update(@Valid @RequestBody ClusterSettingsUpdateDto dto) {
        return ResponseEntity.ok(clusterSettingsService.update(dto));
    }
}
