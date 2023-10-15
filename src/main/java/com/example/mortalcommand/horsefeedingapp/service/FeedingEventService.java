package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.mapper.FeedingEventMapper;
import com.example.mortalcommand.horsefeedingapp.mapper.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingEventRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     * @return a list of all feeding events as FeedingEventResponseDtos wrapped in a ResponseEntity
     */
    public ResponseEntity<List<FeedingEventResponseDto>> readAllFeedingEvents() {
        List<FeedingEvent> allFeedingEvents = feedingEventRepository.findAll();
        return ResponseEntity.ok(feedingEventMapper.feedingEventsToFeedingEventResponseDtos(allFeedingEvents));
    }

    /**
     * Creates a new feeding event object and sets the feeding event to
     * completed when a horse eligible to eat appears.
     * @param horseGuid the GUID of the horse's RFID chip that appears to eat
     * @return either a ResponseEntity with status code 200 and a FeedingEventResponseDto in the ResponseEntity body or a ResponseEntity with status code
     */
    public ResponseEntity<FeedingEventResponseDto> setFeedingEventAsCompleted(String horseGuid) {
        LocalDateTime dateTimeStamp = LocalDateTime.now();

        Optional<Horse> optionalHorse = horseRepository.findHorseByGuid(horseGuid);
        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();

        // Check if horse is allowed to eat
        for (FeedingSchedule fs : allFeedingSchedules) {
            // Check if feeding schedule applies to horse being in request
            if (fs.getHorse() == optionalHorse.get()) {
                // Check if dateTimeStamp lies in time range of feeding schedule
                if (fs.getFeedingStartTime().isBefore(dateTimeStamp) && fs.getFeedingEndTime().isAfter(dateTimeStamp)) {
                    // Create new feeding event
                    FeedingEvent feedingEvent = new FeedingEvent();
                    feedingEvent.setCompleted(true);
                    feedingEvent.setHorse(optionalHorse.get());
                    feedingEvent.setFeedingTime(dateTimeStamp);
                    feedingEvent.setFeedingSchedule(fs);
                    feedingEventRepository.save(feedingEvent);
                    return ResponseEntity.ok(feedingEventMapper.feedingEventToFeedingEventResponseDto(feedingEvent));
                }
            }

        }
        return ResponseEntity.badRequest().build();
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
            if (feedingEventsOfHorse.isEmpty()) {
                continue;
            }
            LocalDateTime latestFeedingEvent = LocalDateTime.MIN;

            // find the event when horse was last time fed
            for (FeedingEvent fe : feedingEventsOfHorse) {
                LocalDateTime feTimeStamp = fe.getFeedingTime();

                if (feTimeStamp.isAfter(latestFeedingEvent)) {
                    latestFeedingEvent = feTimeStamp;
                }
            }
            if (latestFeedingEvent.isBefore(thresholdTime)) {
                unfedHorses.add(horse);
            }
        }
        List<HorseResponseDto> result = horseMapper.horsesToHorseResponseDtos(unfedHorses);
        return result;
    }
}
