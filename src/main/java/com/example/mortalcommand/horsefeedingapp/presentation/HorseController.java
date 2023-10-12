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

    private final HorseRepository horseRepository;
    private final HorseMapper horseMapper;
    private final HorseService horseService;

    public HorseController(HorseRepository horseRepository, HorseMapper horseMapper, HorseService horseService) {
        this.horseRepository = horseRepository;
        this.horseMapper = horseMapper;
        this.horseService = horseService;
    }

    @GetMapping("/horses")
    public ResponseEntity<List<HorseResponseDto>> readHorses() {
        List<Horse> allHorses = horseRepository.findAll();
        List<HorseResponseDto> horseResponseDtos = horseMapper.horsesToHorseResponseDtos(allHorses);
        return ResponseEntity.ok(horseResponseDtos);
    }

    @PostMapping("/horse")
    public ResponseEntity<HorseResponseDto> createHorse(@RequestBody HorseDto horseDto) {
        HorseResponseDto horseResponseDto = horseService.createHorse(horseDto);
        return ResponseEntity.ok(horseResponseDto);
    }

    @DeleteMapping("/horse/{horseId}")
    public ResponseEntity<HorseResponseDto> removeHorse(@PathVariable("horseId") Long id) {
        HorseResponseDto horseResponseDto = horseService.removeHorseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(horseResponseDto);
    }

    @PutMapping("/horse/{horseId}")
    public ResponseEntity<HorseResponseDto> updateHorse(@PathVariable("horseId") Long id, @RequestBody HorseDto horseDto) {
        HorseResponseDto horseResponseDto = horseService.updateHorseById(id, horseDto);
        return ResponseEntity.ok(horseResponseDto);
    }
}
