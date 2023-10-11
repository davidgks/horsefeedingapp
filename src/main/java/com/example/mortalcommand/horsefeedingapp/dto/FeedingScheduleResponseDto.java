package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalTime;

public class FeedingScheduleResponseDto {

    private Long id;
    private String horseGuid;
    private String foodTypeName;
    private LocalTime feedingStartTime;
    private LocalTime feedingEndTime;
    private Long foodQuantityInKg;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorseGuid() {
        return horseGuid;
    }

    public void setHorseGuid(String horseGuid) {
        this.horseGuid = horseGuid;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
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
}
