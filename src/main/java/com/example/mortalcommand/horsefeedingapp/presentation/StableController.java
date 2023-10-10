package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import com.example.mortalcommand.horsefeedingapp.persistence.StableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StableController {

    private final StableRepository stableRepository;

    public StableController(StableRepository stableRepository) {
        this.stableRepository = stableRepository;
    }

    @GetMapping("/stables")
    public ResponseEntity<List<Stable>> readStables() {
        List<Stable> allStables = stableRepository.findAll();
        return ResponseEntity.ok(allStables);
    }

    @GetMapping("/stables/{stableid}")
    public ResponseEntity<Stable> readStableById(@PathVariable("stableid") Long id) {
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
    public ResponseEntity createStable(@RequestBody Stable stable) {
        stableRepository.save(stable);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/stables/{stableId}")
    public ResponseEntity removeStable(@PathVariable("stableId") Long id) {
        stableRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/stables/{stableId}")
    public ResponseEntity<Stable> updateStable(@PathVariable("stableId") Long id, @RequestBody Stable stableDetails) {
        Optional<Stable> updateStable = stableRepository.findById(id);
        if (updateStable.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            updateStable.get().setStableName(stableDetails.getStableName());
            updateStable.get().setLocation(stableDetails.getLocation());
            stableRepository.save(updateStable.get());
        }
        return ResponseEntity.ok(updateStable.orElse(null));
    }
}
