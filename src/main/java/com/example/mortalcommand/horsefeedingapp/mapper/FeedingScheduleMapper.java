package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.entity.FoodType;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FeedingScheduleMapper {

    List<FeedingScheduleDto> fsListToFsDtoList(List<FeedingSchedule> feedingSchedules);
    List<FeedingScheduleResponseDto> fsListToFsResponseDtoList(List<FeedingSchedule> feedingSchedules);

    @Mapping(source = "horse.guid", target = "horseGuid")
    @Mapping(source = "foodType.foodName", target = "foodTypeName")
    FeedingScheduleDto fsToFsDto(FeedingSchedule feedingSchedule);

    @Mapping(source = "horse.guid", target = "horseGuid")
    @Mapping(source = "foodType.foodName", target = "foodTypeName")
    FeedingScheduleResponseDto fsToFsResponseDto(FeedingSchedule feedingSchedule);

    @Mapping(source = "horseGuid", target = "horse.guid")
    @Mapping(source = "foodTypeName", target = "foodType.foodName")
    FeedingSchedule updateFsFromFsDto(FeedingScheduleDto feedingScheduleDto, @MappingTarget FeedingSchedule feedingSchedule);
}
