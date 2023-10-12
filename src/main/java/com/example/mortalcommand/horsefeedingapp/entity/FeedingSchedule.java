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

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "foodtype_id")
    private FoodType foodType;

    private LocalTime feedingStartTime;

    private LocalTime feedingEndTime;

    private Long foodQuantityInKg;

    @OneToMany(mappedBy = "feedingSchedule")
    private Set<FeedingEvent> feedingEvents;

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public LocalTime getFeedingStartTime() {
        return feedingStartTime;
    }

    public void setFeedingStartTime(LocalTime feedingStartTime) {
        this.feedingStartTime = feedingStartTime;
    }

    public LocalTime getFeedingEndTime() {
        return feedingEndTime;
    }

    public void setFeedingEndTime(LocalTime feedingEndTime) {
        this.feedingEndTime = feedingEndTime;
    }

    public Long getFoodQuantityInKg() {
        return foodQuantityInKg;
    }

    public void setFoodQuantityInKg(Long foodQuantityInKg) {
        this.foodQuantityInKg = foodQuantityInKg;
    }

    public Set<FeedingEvent> getFeedingEvents() {
        return feedingEvents;
    }

    public void setFeedingEvents(Set<FeedingEvent> feedingEvents) {
        this.feedingEvents = feedingEvents;
    }
}
