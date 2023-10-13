package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.dto.HorseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import com.example.mortalcommand.horsefeedingapp.entity.Owner;
import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.OwnerRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.StableRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class that handles the business logic related to horses.
 */
@Service
public class HorseService {
    private final HorseRepository horseRepository;
    private final OwnerRepository ownerRepository;
    private final StableRepository stableRepository;
    private final HorseMapper horseMapper;

    /**
     * Constructor for constructing a Horse service.
     * @param horseRepository allows accessing horse related data in the database.
     * @param ownerRepository allows accessing owner related data in the database.
     * @param stableRepository allows accessing stable related data in the database.
     * @param horseMapper implements the logic for mapping horse entities to horseDtos, horseResponseDtos and vice versa
     */
    public HorseService(HorseRepository horseRepository, OwnerRepository ownerRepository, StableRepository stableRepository, HorseMapper horseMapper) {
        this.horseRepository = horseRepository;
        this.ownerRepository = ownerRepository;
        this.stableRepository = stableRepository;
        this.horseMapper = horseMapper;
    }

    /**
     * Reads all horse entities from the database and returns them in a list of HorseResponseDtos
     * @return a list of HorseResponseDtos
     */
    public List<HorseResponseDto> readAllHorses() {
        List<Horse> allHorses = horseRepository.findAll();
        return horseMapper.horsesToHorseResponseDtos(allHorses);
    }

    /**
     * Finds a horse by its GUID in the database and returns it as a HorseResponseDto
     * @param guid can be provided to the method to retrieve the corresponding horse from the database
     * @return a ResponseEntity that wraps a HorseResponseDto
     */
    public ResponseEntity<HorseResponseDto> readHorseByGuid(String guid) {
        Optional<Horse> optionalHorse = horseRepository.findHorseByGuid(guid);
        if (optionalHorse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horseMapper.horseToHorseResponseDto(optionalHorse.get()));
    }

    /**
     * Creates a new Horse and saves it in the database.
     * @param horseDto contains all the information that can be provided to the newly stored horse entity
     * @return a HorseResponseDto of the newly created horse entity
     */
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
        newHorse.setGuid(String.valueOf(UUID.randomUUID())); //necessary for GUID
        newHorse.setOfficialName(horseDto.getOfficialName());
        newHorse.setNickname(horseDto.getNickname());
        newHorse.setBreed(horseDto.getBreed());
        newHorse.setOwner(own);
        newHorse.setStable(stbl);

        // store newly created Horse
        horseRepository.save(newHorse);
        return horseMapper.horseToHorseResponseDto(newHorse);
    }

    /**
     * Deletes a horse identified by its id value from the database.
     * @param id the id value that identifies the horse in the database
     * @return a HorseResponseDto of the deleted horse entity wrapped in a ResponseEntity
     */
    @Transactional
    public ResponseEntity<HorseResponseDto> removeHorseById(Long id) {
        Optional<Horse> optionalHorse = horseRepository.findById(id);
        if (optionalHorse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        horseRepository.deleteById(optionalHorse.get().getId());
        return ResponseEntity.ok(horseMapper.horseToHorseResponseDto(optionalHorse.get()));
    }

    /**
     * Updates a horse with the data provided in the horseDto which is passed as parameter value to the functioin.
     * The horse is identified by its id.
     * @param id identifies the horse in the database
     * @param horseDto contains the data with which the horse is being updated
     * @return HorseResponseDto of the updated horse
     */
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
