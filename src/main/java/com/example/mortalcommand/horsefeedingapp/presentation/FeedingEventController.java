package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.TriggerFeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.service.FeedingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing all http request related to feeding events
 */
@RestController
public class FeedingEventController {

    private final FeedingEventService feedingEventService;

    /**
     * Constructor for constructing the feeding event controller
     * @param feedingEventService contains the business logic for dealing with feeding event entities
     */
    public FeedingEventController(FeedingEventService feedingEventService) {
        this.feedingEventService = feedingEventService;
    }

    @GetMapping("/feedingevents")
    public ResponseEntity<List<FeedingEventResponseDto>> readAllFeedingEvents() {
        return feedingEventService.readAllFeedingEvents();
    }

//    @PostMapping("/feedingevent")
//    public ResponseEntity<FeedingEventResponseDto> setFeedingEventAsCompleted(@RequestBody TriggerFeedingEventDto triggerFeedingEventDto) {
//        return feedingEventService.setFeedingEventAsCompleted(triggerFeedingEventDto);
//    }
    @PostMapping("/feedingevent")
    public ResponseEntity<FeedingEventResponseDto> setFeedingEventAsCompleted(@RequestParam(value = "horseGuid") String horseGuid) {
        return feedingEventService.setFeedingEventAsCompleted(horseGuid);
    }


    @GetMapping("/unfedhorses/{hours}")
    public ResponseEntity<List<HorseResponseDto>> findHorsesNotFedForHours(@PathVariable("hours") Long hours) {
        return ResponseEntity.ok(feedingEventService.findHorsesNotFedForHours(hours));
    }
}
