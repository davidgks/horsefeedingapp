package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for feeding events
 */
public interface FeedingEventRepository extends JpaRepository<FeedingEvent, Long> {
}
