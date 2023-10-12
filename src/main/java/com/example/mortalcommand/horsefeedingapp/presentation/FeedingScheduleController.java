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
 * Rest controller for managing all http request related to the feeding schedule
 */
@RestController
public class FeedingScheduleController {

    private final FeedingService feedingService;

    public FeedingScheduleController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    @GetMapping("/feedingschedules")
    public ResponseEntity<List<FeedingScheduleResponseDto>> readAllFeedingSchedules() {
        return feedingService.getAllFeedingSchedules();
    }

    @PostMapping("/feedingschedules")
    public ResponseEntity<FeedingScheduleResponseDto> createFeedingSchedule(@RequestBody FeedingScheduleDto feedingScheduleDto) {
        return feedingService.createFeedingSchedule(feedingScheduleDto);
    }

    @DeleteMapping("/feedingschedules/{fsId}")
    public ResponseEntity<FeedingScheduleResponseDto> removeFeedingSchedule(@PathVariable("fsId") Long id) {
        return feedingService.removeFeedingScheduleById(id);
    }

    @PutMapping("/feedingschedules/{fsId}")
    public ResponseEntity<FeedingScheduleResponseDto> updateFeedingSchedule(@PathVariable("fsId") Long id, @RequestBody FeedingScheduleDto feedingScheduleDto) {
        return feedingService.updateFeedingScheduleById(id, feedingScheduleDto);
    }

    @GetMapping("/eligibleHorses")
    public ResponseEntity<List<HorseResponseDto>> readAllEligbleHorse(
            @RequestParam(value = "feedingDateTimeToCheck", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime feedingDateTimeToCheck) {
        List<HorseResponseDto> allEligbleHorsesResponse = feedingService.getEligibleHorses(feedingDateTimeToCheck);
        return ResponseEntity.ok(allEligbleHorsesResponse);
    }
}
