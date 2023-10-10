package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfeeding_schedule")
    private Long id;

    @OneToMany
    @JoinColumn(name = "horse_id")
    private Set<Horse> horses;

    @OneToMany
    @JoinColumn(name = "food_type_id")
    private Set<FoodType> foodTypes;

    private LocalTime FeedingStartTime;

    private LocalTime FeedingEndTime;

    private Long FoodQuantityInKg;


}
