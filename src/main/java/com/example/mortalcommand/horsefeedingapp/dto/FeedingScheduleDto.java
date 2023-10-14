package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;

/**
 * Dto that is provided as input to methods related to the http services.
 * It contains information regarding feeding schedules that are entered by the client.
 */
public class FeedingScheduleDto {

    private String horseGuid;
    private String foodTypeName;
    private LocalDateTime FeedingStartTime;
    private LocalDateTime FeedingEndTime;
    private Long FoodQuantityInKg;

    // Getter and setter methods

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

    public LocalDateTime getFeedingStartTime() {
        return FeedingStartTime;
    }

    public void setFeedingStartTime(LocalDateTime feedingStartTime) {
        FeedingStartTime = feedingStartTime;
    }

    public LocalDateTime getFeedingEndTime() {
        return FeedingEndTime;
    }

    public void setFeedingEndTime(LocalDateTime feedingEndTime) {
        FeedingEndTime = feedingEndTime;
    }

    public Long getFoodQuantityInKg() {
        return FoodQuantityInKg;
    }

    public void setFoodQuantityInKg(Long foodQuantityInKg) {
        FoodQuantityInKg = foodQuantityInKg;
    }
}
