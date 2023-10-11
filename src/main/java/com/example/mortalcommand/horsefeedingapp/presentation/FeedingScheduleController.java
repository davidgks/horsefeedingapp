package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.service.FeedingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
