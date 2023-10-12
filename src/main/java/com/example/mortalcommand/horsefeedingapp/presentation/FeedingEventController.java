package com.example.mortalcommand.horsefeedingapp.presentation;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.TriggerFeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.service.FeedingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for managing all http request related to feeding events
 */
@RestController
public class FeedingEventController {

    private final FeedingEventService feedingEventService;

    public FeedingEventController(FeedingEventService feedingEventService) {
        this.feedingEventService = feedingEventService;
    }

    @GetMapping("/feedingevents")
    public ResponseEntity<List<FeedingEventResponseDto>> readAllFeedingEvents() {
        return feedingEventService.readAllFeedingEvents();
    }

    @PostMapping("/feedingEvent")
    public ResponseEntity<?> setFeedingEventAsCompleted(@RequestBody TriggerFeedingEventDto triggerFeedingEventDto) {
        return feedingEventService.setFeedingEventAsCompleted(triggerFeedingEventDto);
    }

//    @PostMapping("/feedingevent")
//    public ResponseEntity<FeedingEventResponseDto> createFeedingEvent(@RequestBody FeedingEventDto feedingEventDto) {
//        return feedingEventService.createFeedingEvent(feedingEventDto);
//    }
}
