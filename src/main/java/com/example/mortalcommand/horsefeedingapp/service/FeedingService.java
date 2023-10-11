package com.example.mortalcommand.horsefeedingapp.service;

import com.example.mortalcommand.horsefeedingapp.FeedingScheduleMapper;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleDto;
import com.example.mortalcommand.horsefeedingapp.dto.FeedingScheduleResponseDto;
import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import com.example.mortalcommand.horsefeedingapp.persistence.FeedingScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final FeedingScheduleMapper feedingScheduleMapper;

    public FeedingService(FeedingScheduleRepository feedingScheduleRepository, FeedingScheduleMapper feedingScheduleMapper) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.feedingScheduleMapper = feedingScheduleMapper;
    }

    public ResponseEntity<List<FeedingScheduleResponseDto>> getAllFeedingSchedules() {
        List<FeedingSchedule> allFeedingSchedules = feedingScheduleRepository.findAll();
        return ResponseEntity.ok(feedingScheduleMapper.fsListToFsResponseDtoList(allFeedingSchedules));
    }


}
