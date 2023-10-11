package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HorseRepository extends JpaRepository<Horse, Long> {

    Optional<Horse> findHorseByOfficialName(String officialName);
}
