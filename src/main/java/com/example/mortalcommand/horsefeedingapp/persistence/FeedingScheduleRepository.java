package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for feeding schedules
 */
public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
}
