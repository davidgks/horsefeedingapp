package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.FeedingEventMapper;
import com.example.mortalcommand.horsefeedingapp.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.TriggerFeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingEventRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class that handles the business logic related to events when horses are fed.
 */
@Service
public class FeedingEventService {

    private final FeedingEventRepository feedingEventRepository;
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final HorseRepository horseRepository;
    private final FeedingEventMapper feedingEventMapper;
    private final HorseMapper horseMapper;

    public FeedingEventService(FeedingEventRepository feedingEventRepository, FeedingScheduleRepository feedingScheduleRepository, HorseRepository horseRepository, FeedingEventMapper feedingEventMapper, HorseMapper horseMapper) {
        this.feedingEventRepository = feedingEventRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.horseRepository = horseRepository;
        this.feedingEventMapper = feedingEventMapper;
        this.horseMapper = horseMapper;
    }

    /**
     * Gets a list of all the feeding events from the database
     */
    public ResponseEntity<List<FeedingEventResponseDto>> readAllFeedingEvents() {
        List<FeedingEvent> allFeedingEvents = feedingEventRepository.findAll();
        return ResponseEntity.ok(feedingEventMapper.feedingEventsToFeedingEventResponseDtos(allFeedingEvents));
    }

    /**
     * Creates a new feeding event object and sets the feeding event to
     * completed when a horse eligible to eat appears.
     * @param triggerFeedingEventDto
     * @return
     */
    public ResponseEntity<?> setFeedingEventAsCompleted(TriggerFeedingEventDto triggerFeedingEventDto) {
        LocalTime timeToCheck = triggerFeedingEventDto.getDateTimeStamp().toLocalTime();
        Optional<Horse> optionalHorse = horseRepository.findHorseByGuid(triggerFeedingEventDto.getHorseGuid());
        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();

        for (FeedingSchedule fs : allFeedingSchedules) {
            if (fs.getFeedingStartTime().isBefore(timeToCheck) && fs.getFeedingEndTime().isAfter(timeToCheck)) {
                FeedingEvent feedingEvent = new FeedingEvent();
                feedingEvent.setCompleted(true);
                feedingEvent.setHorse(optionalHorse.get());
                feedingEvent.setFeedingTime(triggerFeedingEventDto.getDateTimeStamp());
                feedingEventRepository.save(feedingEvent);
                return ResponseEntity.ok(feedingEventMapper.feedingEventToFeedingEventResponseDto(feedingEvent));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Horse with GUID " + triggerFeedingEventDto.getHorseGuid() + " is not allowed to eat right now!");
    }

    /**
     * Finds all horses that were not fed for a certain amount of hours.
     * The amount of hours can be passed as parameter to the method.
     * @param hours the amount of hours a user can enter to determine the threshold for unfed horses
     * @return a list of horses that were not fed for a certain amount of hours
     */
    public List<HorseResponseDto> findHorsesNotFedForHours(Long hours) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime thresholdTime = currentTime.minusHours(hours);

        List<Horse> unfedHorses = new ArrayList<>();
        List<Horse> allHorses = horseRepository.findAll();

        for (Horse horse : allHorses) {
            Set<FeedingEvent> feedingEventsOfHorse = horse.getFeedingEvents();
            LocalDateTime latestFeedingEvent = LocalDateTime.MIN;

            // find the event when horse was last time fed
            for (FeedingEvent fe : feedingEventsOfHorse) {
                LocalDateTime feTimeStamp = fe.getFeedingTime();
//                if (feTimeStamp == null) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("")
//                }
                if (feTimeStamp.isAfter(latestFeedingEvent)) {
                    latestFeedingEvent = feTimeStamp;
                }
            }
            // if horse was last time fed before the threshold, it is considered as unfed
            if (latestFeedingEvent.isBefore(thresholdTime)) {
                unfedHorses.add(horse);
            }
        }
        return horseMapper.horsesToHorseResponseDtos(unfedHorses);
    }

    // TODO: can be deleted
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
