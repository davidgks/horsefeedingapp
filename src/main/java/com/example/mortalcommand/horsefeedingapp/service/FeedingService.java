package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.FeedingScheduleMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.*;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.FoodTypeRepository;
import com.example.mortalcommand.horsefeedingapp.persistence.HorseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final HorseRepository horseRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;

    public FeedingService(FeedingScheduleRepository feedingScheduleRepository, HorseRepository horseRepository, FoodTypeRepository foodTypeRepository, FeedingScheduleMapper feedingScheduleMapper) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.horseRepository = horseRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.feedingScheduleMapper = feedingScheduleMapper;
    }

    public ResponseEntity<List<FeedingScheduleResponseDto>> getAllFeedingSchedules() {
        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();
        return ResponseEntity.ok(feedingScheduleMapper.fsListToFsResponseDtoList(allFeedingSchedules));
    }

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

}
