package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 *  Maps FeedingSchedule objects to FeedingEventResponseDto objects
 */
@Mapper(componentModel = "spring")
public interface FeedingScheduleMapper {

    /**
     * Maps a list of FeedingSchedule objects to a list of FeedingScheduleResponseDto objects
     * @param feedingSchedules a list of feeding schedule objects
     * @return a list of feeding event schedule dto objects
     */
    List<FeedingScheduleResponseDto> fsListToFsResponseDtoList(List<FeedingSchedule> feedingSchedules);

    /**
     * Maps a FeedingSchedule object to a FeedingScheduleResponseDto object
     * @param feedingSchedule a feeding schedule object
     * @return a feeding schedule response dto object
     */
    @Mapping(source = "horse.guid", target = "horseGuid")
    @Mapping(source = "foodType.foodName", target = "foodTypeName")
    FeedingScheduleResponseDto fsToFsResponseDto(FeedingSchedule feedingSchedule);

    /**
     * Maps a FeedingScheduleDto object to a FeedingSchedule object
     * @param feedingScheduleDto a feeding schedule dto object
     * @param feedingSchedule a feeding schedule object
     * @return a feeding schedule object
     */
    @Mapping(source = "horseGuid", target = "horse.guid")
    @Mapping(source = "foodTypeName", target = "foodType.foodName")
    FeedingSchedule updateFsFromFsDto(FeedingScheduleDto feedingScheduleDto, @MappingTarget FeedingSchedule feedingSchedule);
}