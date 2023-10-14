package com.example.mortalcommand.horsefeedingapp.mapper;

import com.example.mortalcommand.horsefeedingapp.dto.StableDto;
import com.example.mortalcommand.horsefeedingapp.dto.StableResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Maps Stable objects to StableResponseDto, StableDto objects or vice versa
 */
@Mapper(componentModel = "spring")
public interface StableMapper {

    /**
     * Maps a list of Stable objects to a list of StableResponseDto objects
     * @param stables a list of Stable objects
     * @return a list of StableResponseDto objects
     */
    List<StableResponseDto> stablesToStableResponseDtos(List<Stable> stables);

    /**
     * Maps a StableDto object to a Stable object
     * @param stableDto a stableDto object
     * @return a Stable object
     */
    Stable stableDtoToStable(StableDto stableDto);

    /**
     * Maps a Stable object to a StableResponseDto object
     * @param stable a Stable object
     * @return a StableResponseDto object
     */
    StableResponseDto stableToStableResponseDto(Stable stable);
}
