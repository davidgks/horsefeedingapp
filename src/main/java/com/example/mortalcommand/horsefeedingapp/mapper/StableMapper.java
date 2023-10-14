package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.StableDto;
import com.example.mortalcommand.horsefeedingapp.dto.StableResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StableMapper {

    List<StableDto> stablesToStableDtos(List<Stable> stables);
    List<StableResponseDto> stablesToStableResponseDtos(List<Stable> stables);

    StableDto stableToStableDto(Stable stable);

    Stable stableDtoToStable(StableDto stableDto);

    StableResponseDto stableToStableResponseDto(Stable stable);

    Stable updateStableFromStableDto(StableDto stableDto, @MappingTarget Stable stable);
}
