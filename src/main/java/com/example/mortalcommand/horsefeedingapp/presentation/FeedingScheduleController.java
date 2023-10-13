package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.service.FeedingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller for managing all http requests related to the feeding schedule
 */
@RestController
public class FeedingScheduleController {

    private final FeedingService feedingService;

    /**
     * Constructor for constructing the feeding schedule controller
     * @param feedingService contains the business logic for dealing with feeding schedule entities
     */
    public FeedingScheduleController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    /**
     * Reads all feeding schedule entities from the database by calling the respective method of the feedingService
     * @return a list of FeedingScheduleResponseDto wrapped in a ResponseEntity
     */
    @GetMapping("/feedingschedules")
    public ResponseEntity<List<FeedingScheduleResponseDto>> readAllFeedingSchedules() {
        return feedingService.getAllFeedingSchedules();
    }

    /**
     * Creates and adds a new feeding schedule entity to the database by calling the respective method of the feedingService.
     * @param feedingScheduleDto contains the data which will be written to the newly created feeding schedule entity.
     * @return a FeedingScheduleResponseDto wrapped in a ResponseEntity
     */
    @PostMapping("/feedingschedules")
    public ResponseEntity<FeedingScheduleResponseDto> createFeedingSchedule(@RequestBody FeedingScheduleDto feedingScheduleDto) {
        return feedingService.createFeedingSchedule(feedingScheduleDto);
    }

    /**
     * Deletes a feeding schedule entity from the database by calling the respective method of the feedingService.
     * The feeding schedule entity is identified by its id.
     * @param id the value that identifies the feeding schedule entity in the database
     * @return a FeedingScheduleResponseDto wrapped in a ResponseEntity
     */
    @DeleteMapping("/feedingschedules/{fsId}")
    public ResponseEntity<FeedingScheduleResponseDto> removeFeedingSchedule(@PathVariable("fsId") Long id) {
        return feedingService.removeFeedingScheduleById(id);
    }

    /**
     * Updates a feeding schedule entity in the database by calling the respective method of the feedingService.
     * The feeding schedule entity going to be updated is identified by its id.
     * @param id the value that identifies the feeding schedule entity in the database.
     * @param feedingScheduleDto contains the data with which the feeding schedule entity is being updated
     * @return a FeedingScheduleResponseDto wrapped in a ResponseEntity
     */
    @PutMapping("/feedingschedules/{fsId}")
    public ResponseEntity<FeedingScheduleResponseDto> updateFeedingSchedule(@PathVariable("fsId") Long id, @RequestBody FeedingScheduleDto feedingScheduleDto) {
        return feedingService.updateFeedingScheduleById(id, feedingScheduleDto);
    }

    /**
     * Finds all horses that are eligible to being fed for a certain time by calling the respective method in the feedingService.
     * @param feedingDateTimeToCheck the feeding time that is being checked to determine whether a horse is eligible to eat
     * @return a list of all horses that are eligible to eat wrapped in a ResponseEntity
     */
    @GetMapping("/eligibleHorses")
    public ResponseEntity<List<HorseResponseDto>> readAllEligbleHorse(
            @RequestParam(value = "feedingDateTimeToCheck", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime feedingDateTimeToCheck) {
        List<HorseResponseDto> allEligbleHorsesResponse = feedingService.getEligibleHorses(feedingDateTimeToCheck);
        return ResponseEntity.ok(allEligbleHorsesResponse);
    }
}
