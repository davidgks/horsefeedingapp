package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.*;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Maps FeedingEvent objects to FeedingEventResponseDto objects
 */
@Mapper(componentModel = "spring")
public interface FeedingEventMapper {

    /**
     * Maps a list of FeedingEvent objects to a list of FeedingEventResponseDto objects
     * @param feedingEvents a list of feeding event objects
     * @return a list of feeding event response dto objects
     */
    List<FeedingEventResponseDto> feedingEventsToFeedingEventResponseDtos(List<FeedingEvent> feedingEvents);

    /**
     * Maps a FeedingEvent object to a FeedingEventResponseDto object
     * @param feedingEvent a feeding event object
     * @return a feeding event response dto object
     */
    @Mapping(source = "horse.guid", target = "horseGuid")
    @Mapping(source = "feedingSchedule.feedingStartTime", target = "feedingStartTime")
    @Mapping(source = "feedingSchedule.feedingEndTime", target = "feedingEndTime")
    FeedingEventResponseDto feedingEventToFeedingEventResponseDto(FeedingEvent feedingEvent);



}
