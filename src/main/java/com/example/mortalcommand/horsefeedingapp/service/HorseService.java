package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.StableMapper;
import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.entity.Owner;
import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.OwnerRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.StableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class HorseService {
    private final HorseRepository horseRepository;
    private final OwnerRepository ownerRepository;
    private final StableRepository stableRepository;
    private final HorseMapper horseMapper;

    @Autowired
    public HorseService(HorseRepository horseRepository, OwnerRepository ownerRepository, StableRepository stableRepository, HorseMapper horseMapper) {
        this.horseRepository = horseRepository;
        this.ownerRepository = ownerRepository;
        this.stableRepository = stableRepository;
        this.horseMapper = horseMapper;
    }

    //TODO !!!!!!!!!!!!!
    public HorseResponseDto createHorse(HorseDto horseDto) {
        Optional<Owner> optionalOwner = ownerRepository.findOnwerByOwnerName(horseDto.getOwnerName());
        Optional<Stable> optionalStable = stableRepository.findStableByStableName(horseDto.getStableName());

        Owner own;
        if (optionalOwner.isEmpty()) {
            own = new Owner();
            own.setOwnerName(horseDto.getOwnerName());
            ownerRepository.save(own);
        } else {
            own = optionalOwner.get();
        }

        Stable stbl;
        if (optionalStable.isEmpty()) {
            stbl = new Stable();
            stbl.setStableName(horseDto.getStableName());
            stableRepository.save(stbl);
        } else {
            stbl = optionalStable.get();
        }

        // Create new horse
        Horse newHorse = new Horse();
        newHorse.setGuid(horseDto.getGuid());
        newHorse.setOfficialName(horseDto.getOfficialName());
        newHorse.setNickname(horseDto.getNickname());
        newHorse.setBreed(horseDto.getBreed());
        newHorse.setOwner(own);
        newHorse.setStable(stbl);
        // store newly created Horse
        horseRepository.save(newHorse);
        return horseMapper.horseToHorseResponseDto(newHorse);
    }

    public HorseResponseDto removeHorseById(Long horseId) {
        Optional<Horse> optionalHorse = horseRepository.findById(horseId);
        if (optionalHorse.isEmpty()) {
            return new HorseResponseDto();
        }
        HorseResponseDto horseResponseDto = horseMapper.horseToHorseResponseDto(optionalHorse.get());
        horseRepository.deleteById(horseId);
        return horseResponseDto;
    }

    public HorseResponseDto updateHorseById(Long id, HorseDto horseDto) {
        Optional<Horse> optionalHorse = horseRepository.findById(id);
        if (optionalHorse.isEmpty()) {
            return new HorseResponseDto();
        }
        Horse updatedHorse = horseMapper.updateHorseFromHorseDto(horseDto, optionalHorse.get());
        horseRepository.save(updatedHorse);
        return horseMapper.horseToHorseResponseDto(updatedHorse);
    }
}
