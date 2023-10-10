package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.StableMapper;
import com.example.mortalcommand.horsefeedingapp.dto.StableDto;
import com.example.mortalcommand.horsefeedingapp.dto.StableResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import com.example.mortalcommand.horsefeedingapp.persistence.StableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StableController {

    private final StableRepository stableRepository;
    private final StableMapper stableMapper;

    public StableController(StableRepository stableRepository, StableMapper stableMapper) {
        this.stableRepository = stableRepository;
        this.stableMapper = stableMapper;
    }

    @GetMapping("/stables")
    public ResponseEntity<List<StableDto>> readStables() {
        List<Stable> allStables = stableRepository.findAll();
        List<StableDto> stablesDtos = stableMapper.stablesToStableDtos(allStables);
        return ResponseEntity.ok(stablesDtos);
    }

    @GetMapping("/stables/{stableid}")
    public ResponseEntity readStableById(@PathVariable("stableid") Long id) {
        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Stable> optionalStable = stableRepository.findById(id);
        if (optionalStable.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalStable.orElse(null));
    }

    @PostMapping("/stables")
    public ResponseEntity<StableResponseDto> createStable(@RequestBody StableDto stableDto) {
        Stable stable = stableMapper.stableDtoToStable(stableDto);
        stableRepository.save(stable);
        StableResponseDto stableResponseDto = stableMapper.stableToStableResponseDto(stable);
        return ResponseEntity.ok(stableResponseDto);
    }

    @DeleteMapping("/stables/{stableId}")
    public ResponseEntity<StableResponseDto> removeStable(@PathVariable("stableId") Long id) {
        Optional<Stable> optionalStable = stableRepository.findById(id);
        StableResponseDto stableResponseDto = stableMapper.stableToStableResponseDto(optionalStable.get());
        stableRepository.deleteById(id);
        return ResponseEntity.ok(stableResponseDto);
    }

    @PutMapping("/stables/{stableId}")
    public ResponseEntity<StableResponseDto> updateStable(@PathVariable("stableId") Long id, @RequestBody StableDto stableDetails) {
        Optional<Stable> updateStable = stableRepository.findById(id);

        if (updateStable.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            updateStable.get().setStableName(stableDetails.getStableName());
            updateStable.get().setLocation(stableDetails.getLocation());
            stableRepository.save(updateStable.get());
            StableResponseDto stableResponseDto = stableMapper.stableToStableResponseDto(updateStable.get());
            return ResponseEntity.ok(stableResponseDto);
        }

    }
}
