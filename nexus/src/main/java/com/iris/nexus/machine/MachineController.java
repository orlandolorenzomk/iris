package com.iris.nexus.machine;

import com.iris.nexus.machine.internal.MachineDto;
import com.iris.nexus.shared.constants.UrlMapping;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(UrlMapping.MACHINES_BASE_URL)
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @GetMapping("/{id}")
    public ResponseEntity<MachineDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(machineService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MachineDto>> findAll() {
        return ResponseEntity.ok(machineService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MachineCreateDto dto) {
        machineService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody MachineUpdateDto dto) {
        machineService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        machineService.delete(id);
        return ResponseEntity.ok().build();
    }
}
