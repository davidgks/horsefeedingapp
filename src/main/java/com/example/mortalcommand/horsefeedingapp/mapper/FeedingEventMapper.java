package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.*;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedingEventMapper {

    List<FeedingEventResponseDto> feedingEventsToFeedingEventResponseDtos(List<FeedingEvent> feedingEvents);

    /**
     * Maps a FeedingEvent object to a FeedingEventResponseDto object
     * @param feedingEvent
     * @return
     */
    @Mapping(source = "horse.guid", target = "horseGuid")
    @Mapping(source = "feedingSchedule.feedingStartTime", target = "feedingStartTime")
    @Mapping(source = "feedingSchedule.feedingEndTime", target = "feedingEndTime")
    FeedingEventResponseDto feedingEventToFeedingEventResponseDto(FeedingEvent feedingEvent);



}
