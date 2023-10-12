package com.example.mortalcommand.horsefeedingapp.persistence;

import com.example.mortalcommand.horsefeedingapp.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for food types
 */
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {

    /**
     * Finds food by the name of the food type and retrieves it from the database
     */
    Optional<FoodType> findFoodTypeByFoodName(String foodName);
}
