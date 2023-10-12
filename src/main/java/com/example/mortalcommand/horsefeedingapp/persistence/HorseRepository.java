package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for horses
 */
public interface HorseRepository extends JpaRepository<Horse, Long> {

    /**
     * Finds a horse by its name and retrieves it from the database
     */
    Optional<Horse> findHorseByOfficialName(String officialName);

    /**
     * Finds a horse by its guid and retrieves it from the database
     */
    Optional<Horse> findHorseByGuid(String horseGuid);
}
