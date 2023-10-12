package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for owners
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    /**
     * Finds an owner by its name and retrieves it from the database
     */
    Optional<Owner> findOnwerByOwnerName(String ownerName);
}
