package com.example.mortalcommand.horsefeedingapp;

import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorseMapper {

    List<HorseDto> horsesToHorseDtos(List<Horse> horses);

    HorseDto horseToHorseDto(Horse horse);

    Horse horseDtoToHorse(HorseDto horseDto);

    HorseResponseDto horseToHorseResponseDto(Horse horse);
}
