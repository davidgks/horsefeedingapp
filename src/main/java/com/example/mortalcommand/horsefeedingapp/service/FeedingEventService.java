package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.FeedingEventMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FeedingEventService {

    private final FeedingEventRepository feedingEventRepository;
    private final FeedingEventMapper feedingEventMapper;

    public FeedingEventService(FeedingEventRepository feedingEventRepository, FeedingEventMapper feedingEventMapper) {
        this.feedingEventRepository = feedingEventRepository;
        this.feedingEventMapper = feedingEventMapper;
    }

    public ResponseEntity<List<FeedingEventResponseDto>> readAllFeedingEvents() {
        List<FeedingEvent> allFeedingEvents = feedingEventRepository.findAll();
        return ResponseEntity.ok(feedingEventMapper.feedingEventsToFeedingEventResponseDtos(allFeedingEvents));
    }

    public void createFeedingEvent(FeedingEventDto feedingEventDto) {
        LocalDateTime dateTimeStamp = LocalDateTime.now();
        LocalTime timeStamp = dateTimeStamp.toLocalTime();

        LocalTime feedingStartTime = feedingEventDto.getFeedingStartTime();
        LocalTime feedingEndTime = feedingEventDto.getFeedingEndTime();

        FeedingEvent newFeedingEvent = new FeedingEvent();

        if (timeStamp.isAfter(feedingStartTime) && timeStamp.isBefore(feedingEndTime)) {
            newFeedingEvent.setFeedingTime(dateTimeStamp);
            newFeedingEvent.setCompleted(true);
        } else {
            newFeedingEvent.setFeedingTime(null);
            newFeedingEvent.setCompleted(false);
        }
    }
}
