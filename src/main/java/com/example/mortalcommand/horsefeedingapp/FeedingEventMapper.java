package com.example.mortalcommand.horsefeedingapp;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingEventResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedingEventMapper {

    List<FeedingEventResponseDto> feedingEventsToFeedingEventResponseDtos(List<FeedingEvent> feedingEvents);


    @Mapping(source = "horse.guid", target = "horseGuid")
//    @Mapping(source = "feedingSchedule", target = "feedingScheduleResponseDto")
    FeedingEventDto feedingEventToFeedingEventDto(FeedingEvent feedingEvent);

//    @Mapping(source = "horse.guid", target = "horseGuid")
//    @Mapping(source = "feedingSchedule.feedingStartTime", target = "feedingStartTime")
//    @Mapping(source = "feedingSchedule.feedingEndTime", target = "feedingEndTime")
//    FeedingEventResponseDto feedingEventToFeedingEventResponseDto(FeedingEvent feedingEvent);
//
//    @Mapping(source = "", target = "")
//    FeedingEvent feedingEventDtoToFeedingEvent(FeedingEventDto feedingEventDto);

}
