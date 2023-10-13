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

    /**
     * Constructor for constructing the horse controller
     * @param horseService contains the business logic for dealing with horse entities
     */
    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    /**
     * Reads all horses from the database by calling the respective method of the horseService
     * @return a list of HorseResponseDto wrapped in a ResponseEntity
     */
    @GetMapping("/horse")
    public ResponseEntity<List<HorseResponseDto>> readAllHorses() {
        return ResponseEntity.ok(horseService.readAllHorses());
    }

    /**
     * Reads a single horse from the database by calling the respective method of the horseService.
     * The horse is identified by its GUID.
     * @param guid entifies the horse by which the horse is retrieved from the database
     * @return a HorseResponseDto wrapped in a ResponseEntity
     */
    @GetMapping("/horse/{horseGuid}")
    public ResponseEntity<HorseResponseDto> readHorseByGuid(@PathVariable("horseGuid") String guid) {
        return horseService.readHorseByGuid(guid);
    }

    /**
     * Creates and adds a new horse to the database by calling the respective method of the horseService.
     * @param horseDto contains the data which will be written to the newly created horse.
     * @return a HorseResponseDto wrapped in a ResponseEntity
     */
    @PostMapping("/horse")
    public ResponseEntity<HorseResponseDto> createHorse(@RequestBody HorseDto horseDto) {
        return ResponseEntity.ok(horseService.createHorse(horseDto));
    }

    /**
     * Deletes a horse entity from the database by calling the respective method of the horseService.
     * The horse is identified by its id.
     * @param id the value that identifies the horse in the database
     * @return a HorseResponseDto wrapped in a ResponseEntity
     */
    @DeleteMapping("/horse/{horseId}")
    public ResponseEntity<HorseResponseDto> removeHorse(@PathVariable("horseId") Long id) {
        return horseService.removeHorseById(id);
    }

    /**
     * Updates a horse in the database by calling the respective method of the horseService.
     * The horse going to be updated is identified by its id.
     * @param id the value that identifies the horse in the database.
     * @param horseDto contains the data with which the horse entity is being updated
     * @return a HorseResponseDto wrapped in a ResponseEntity
     */
    @PutMapping("/horse/{horseId}")
    public ResponseEntity<HorseResponseDto> updateHorse(@PathVariable("horseId") Long id, @RequestBody HorseDto horseDto) {
        return ResponseEntity.ok(horseService.updateHorseById(id, horseDto));
    }
}
