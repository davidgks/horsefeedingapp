package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalTime;

public class FeedingScheduleDto {

    private Long id;
    private String horseGuid;
    private String foodTypeName;
    private LocalTime FeedingStartTime;
    private LocalTime FeedingEndTime;
    private Long FoodQuantityInKg;

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
        return FeedingStartTime;
    }

    public void setFeedingStartTime(LocalTime feedingStartTime) {
        FeedingStartTime = feedingStartTime;
    }

    public LocalTime getFeedingEndTime() {
        return FeedingEndTime;
    }

    public void setFeedingEndTime(LocalTime feedingEndTime) {
        FeedingEndTime = feedingEndTime;
    }

    public Long getFoodQuantityInKg() {
        return FoodQuantityInKg;
    }

    public void setFoodQuantityInKg(Long foodQuantityInKg) {
        FoodQuantityInKg = foodQuantityInKg;
    }
}
