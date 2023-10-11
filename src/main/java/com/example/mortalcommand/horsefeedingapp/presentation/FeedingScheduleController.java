package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.service.FeedingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedingScheduleController {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final FeedingService feedingService;

    public FeedingScheduleController(FeedingScheduleRepository feedingScheduleRepository, FeedingService feedingService) {
        this.feedingScheduleRepository = feedingScheduleRepository;
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
        return feedingService.removeFeedingSchedule(id);
    }



}
