package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.FeedingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for feeding events
 */
public interface FeedingEventRepository extends JpaRepository<FeedingEvent, Long> {
}
