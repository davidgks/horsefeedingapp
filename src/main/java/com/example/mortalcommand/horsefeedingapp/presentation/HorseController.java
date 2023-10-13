package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import com.example.mortalcommand.horsefeedingapp.service.HorseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing all http request related to horses
 */
@RestController
public class HorseController {

    private final HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping("/horse")
    public ResponseEntity<List<HorseResponseDto>> readAllHorses() {
        return ResponseEntity.ok(horseService.readAllHorses());
    }

    @GetMapping("/horse/{horseGuid}")
    public ResponseEntity<HorseResponseDto> readHorseByGuid(@PathVariable("horseGuid") String guid) {
        return horseService.readHorseByGuid(guid);
    }

    @PostMapping("/horse")
    public ResponseEntity<HorseResponseDto> createHorse(@RequestBody HorseDto horseDto) {
        return ResponseEntity.ok(horseService.createHorse(horseDto));
    }

    @DeleteMapping("/horse/{horseId}")
    public ResponseEntity<HorseResponseDto> removeHorse(@PathVariable("horseId") Long id) {
        return horseService.removeHorseByGuid(id);
    }

    @PutMapping("/horse/{horseGuid}")
    public ResponseEntity<HorseResponseDto> updateHorse(@PathVariable("horseGuid") Long id, @RequestBody HorseDto horseDto) {
        return ResponseEntity.ok(horseService.updateHorseById(id, horseDto));
    }
}
