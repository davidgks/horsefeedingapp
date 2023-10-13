package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.FeedingScheduleMapper;
import com.example.mortalcommand.horsefeedingapp.HorseMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.dto.HorseResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.*;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.FoodTypeRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class that handles the business logic for maintaining feeding preferences of horses
 * and managing feeding schedules.
 */
@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final HorseRepository horseRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;
    private final HorseMapper horseMapper;

    /**
     * Constructor for constructing the feeding schedule service.
     * @param feedingScheduleRepository allows accessing feeding schedule related data in the database.
     * @param horseRepository allows accessing horse related data in the database.
     * @param foodTypeRepository allows accessing food type related data in the database.
     * @param feedingScheduleMapper implements the logic for mapping feeding schedule entities to feedingScheduleDtos, feedingScheduleDtos and vice versa
     * @param horseMapper implements the logic for mapping horse entities to horseDtos, horseResponseDtos and vice versa
     */
    public FeedingService(FeedingScheduleRepository feedingScheduleRepository, HorseRepository horseRepository, FoodTypeRepository foodTypeRepository, FeedingScheduleMapper feedingScheduleMapper, HorseMapper horseMapper) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.horseRepository = horseRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.feedingScheduleMapper = feedingScheduleMapper;
        this.horseMapper = horseMapper;
    }

    /**
     * Reads all feeding schedule entities from the database and returns them in a list of FeedingScheduleResponseDto
     * @return a list of FeedingScheduleResponseDto
     */
    public ResponseEntity<List<FeedingScheduleResponseDto>> getAllFeedingSchedules() {
        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();
        return ResponseEntity.ok(feedingScheduleMapper.fsListToFsResponseDtoList(allFeedingSchedules));
    }

    /**
     * Creates a new feeding schedule entity and saves it in the database.
     * @param feedingScheduleDto contains all the information that can be provided to the newly stored feeding schedule entity
     * @return a FeedingScheduleResponseDto of the newly created feeding schedule entity wrapped in a ResponseEntity
     */
    public ResponseEntity<FeedingScheduleResponseDto> createFeedingSchedule(FeedingScheduleDto feedingScheduleDto) {
        Optional<Horse> optionalHorse = horseRepository.findHorseByGuid(feedingScheduleDto.getHorseGuid());
        Optional<FoodType> optionalFoodType = foodTypeRepository.findFoodTypeByFoodName(feedingScheduleDto.getFoodTypeName());

        Horse hrs;
        if (optionalHorse.isEmpty()) {
            hrs = new Horse();
            hrs.setGuid(feedingScheduleDto.getHorseGuid());
            horseRepository.save(hrs);
        } else {
            hrs = optionalHorse.get();
        }

        FoodType foodTp;
        if (optionalFoodType.isEmpty()) {
            foodTp = new FoodType();
            foodTp.setFoodName(feedingScheduleDto.getFoodTypeName());
            foodTypeRepository.save(foodTp);
        } else {
            foodTp = optionalFoodType.get();
        }

        // Create new feeding schedule
        FeedingSchedule newFeedingSchedule = new FeedingSchedule();
        newFeedingSchedule.setHorse(hrs);
        newFeedingSchedule.setFoodType(foodTp);
        newFeedingSchedule.setFeedingStartTime(feedingScheduleDto.getFeedingStartTime());
        newFeedingSchedule.setFeedingEndTime(feedingScheduleDto.getFeedingEndTime());
        newFeedingSchedule.setFoodQuantityInKg(feedingScheduleDto.getFoodQuantityInKg());
        feedingScheduleRepository.save(newFeedingSchedule);
        return ResponseEntity.ok(feedingScheduleMapper.fsToFsResponseDto(newFeedingSchedule));
    }

    /**
     * Deletes a feeding schedule entity identified by its id value from the database.
     * @param id the id value that identifies the feeding schedule in the database
     * @return a FeedingScheduleResponseDto of the deleted feeding schedule entity wrapped in a ResponseEntity
     */
    public ResponseEntity<FeedingScheduleResponseDto> removeFeedingScheduleById(Long id) {
        Optional<FeedingSchedule> optionalFs = feedingScheduleRepository.findById(id);

        if (optionalFs.isEmpty()) {
            FeedingScheduleResponseDto emptyFsResponseDto = new FeedingScheduleResponseDto();
            return ResponseEntity.ok(emptyFsResponseDto);
        }
        FeedingScheduleResponseDto feedingScheduleResponseDto = feedingScheduleMapper.fsToFsResponseDto(optionalFs.get());
        feedingScheduleRepository.deleteById(id);
        return ResponseEntity.ok(feedingScheduleResponseDto);
    }

    /**
     * Updates a feeding schedule entity with the data provided in the FeedingScheduleDto which is passed as parameter value to the function.
     * The feeding schedule is identified by its id.
     * @param id identifies the feeding schedule in the database
     * @param feedingScheduleDto contains the data with which the feeding schedule is being updated
     * @return FeedingScheduleResponseDto of the updated feeding schedule wrapped in a ResponseEntity
     */
    public ResponseEntity<FeedingScheduleResponseDto> updateFeedingScheduleById(Long id, FeedingScheduleDto feedingScheduleDto) {
        Optional<FeedingSchedule> optionalFs = feedingScheduleRepository.findById(id);

        if (optionalFs.isEmpty()) {
            FeedingScheduleResponseDto emptyFsResponseDto = new FeedingScheduleResponseDto();
            return ResponseEntity.ok(emptyFsResponseDto);
        }
        FeedingSchedule updatedFeedingSchedule = feedingScheduleMapper.updateFsFromFsDto(feedingScheduleDto, optionalFs.get());
        feedingScheduleRepository.save(updatedFeedingSchedule);
        return ResponseEntity.ok(feedingScheduleMapper.fsToFsResponseDto(updatedFeedingSchedule));
    }

    /**
     * Finds all horses that are eligible to being fed for a certain time.
     * @param feedingDateTimeToCheck the feeding time tha is being check to determine whether a horse is eligible to eat
     * @return a list of all horses that are eligible to eat.
     */
    public List<HorseResponseDto> getEligibleHorses(LocalDateTime feedingDateTimeToCheck) {
        List<Horse> allEligibleHorses = new ArrayList<>();
        LocalTime feedingTimeToCheck = feedingDateTimeToCheck.toLocalTime();

        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();
        for (FeedingSchedule fs : allFeedingSchedules) {
            if (fs.getFeedingStartTime().isBefore(feedingTimeToCheck) && fs.getFeedingEndTime().isAfter(feedingTimeToCheck)) {
                Horse eligibleHorse = fs.getHorse();
                allEligibleHorses.add(eligibleHorse);
            }
        } return horseMapper.horsesToHorseResponseDtos(allEligibleHorses);
    }
}
