package com.example.mortalcommand.horsefeedingapp;

import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorseMapper {

    List<HorseDto> horsesToHorseDtos(List<Horse> horses);
    List<HorseResponseDto> horsesToHorseResponseDtos(List<Horse> horses);

    @Mapping(source = "owner.ownerName", target = "ownerName")
    @Mapping(source = "stable.stableName", target = "stableName")
    HorseDto horseToHorseDto(Horse horse);

    @Mapping(source = "ownerName", target = "owner.ownerName")
    @Mapping(source = "stableName", target = "stable.stableName")
    Horse horseDtoToHorse(HorseDto horseDto);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.ownerName", target = "ownerName")
    @Mapping(source = "stable.id", target = "stableId")
    @Mapping(source = "stable.stableName", target = "stableName")
    HorseResponseDto horseToHorseResponseDto(Horse horse);

    @Mapping(source = "ownerName", target = "owner.ownerName")
    @Mapping(source = "stableName", target = "stable.stableName")
    Horse updateHorseFromHorseDto(HorseDto horseDto, @MappingTarget Horse horse);
}
