package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.Stable;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for stables
 */
public interface StableRepository extends JpaRepository<Stable, Long> {

    /**
     * Finds a stable by its name and retrieves it from the database
     */
    Optional<Stable> findStableByStableName(String stableName);
}
