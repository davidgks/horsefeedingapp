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

@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final HorseRepository horseRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;
    private final HorseMapper horseMapper;

    public FeedingService(FeedingScheduleRepository feedingScheduleRepository, HorseRepository horseRepository, FoodTypeRepository foodTypeRepository, FeedingScheduleMapper feedingScheduleMapper, HorseMapper horseMapper) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.horseRepository = horseRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.feedingScheduleMapper = feedingScheduleMapper;
        this.horseMapper = horseMapper;
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
