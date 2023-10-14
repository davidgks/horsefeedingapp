package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Maps Horse objects to HorseResponseDto, HorseDto objects or vice versa
 */
@Mapper(componentModel = "spring")
public interface HorseMapper {

    /**
     * Currently not used. Might be useful when extending the application with further features or functionalities
     * @param horses a list of Horse objects
     * @return a list of HorseDto objects
     */
    List<HorseDto> horsesToHorseDtos(List<Horse> horses);

    /**
     * Maps a list of Horse objects to a list of HorseResponseDto objects
     * @param horses a list of Horse objects
     * @return a list of HorseResponseDto objects
     */
    List<HorseResponseDto> horsesToHorseResponseDtos(List<Horse> horses);

    /**
     * Currently not used. Might be useful when extending the application with further features or functionalities
     * @param horse a Horse object
     * @return a HorseDto object
     */
    @Mapping(source = "owner.ownerName", target = "ownerName")
    @Mapping(source = "stable.stableName", target = "stableName")
    HorseDto horseToHorseDto(Horse horse);

    /**
     * Currently not used. Might be useful when extending the application with further features or functionalities
     * @param horseDto a HorseDto object
     * @return a Horse object
     */
    @Mapping(source = "ownerName", target = "owner.ownerName")
    @Mapping(source = "stableName", target = "stable.stableName")
    Horse horseDtoToHorse(HorseDto horseDto);

    /**
     * Maps a Horse object to a HorseResponseDto object
     * @param horse a Horse object
     * @return a HorseResponseDto object
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "guid", target = "guid")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.ownerName", target = "ownerName")
    @Mapping(source = "stable.id", target = "stableId")
    @Mapping(source = "stable.stableName", target = "stableName")
    @Mapping(source = "feedingEvents", target = "feedingEventResponseDtos")
    HorseResponseDto horseToHorseResponseDto(Horse horse);

    /**
     * Maps a HorseDto object to a Horse object
     * @param horseDto a HorseDto object
     * @param horse a Horse object
     * @return a Horse object
     */
    @Mapping(source = "ownerName", target = "owner.ownerName")
    @Mapping(source = "stableName", target = "stable.stableName")
    Horse updateHorseFromHorseDto(HorseDto horseDto, @MappingTarget Horse horse);
}
