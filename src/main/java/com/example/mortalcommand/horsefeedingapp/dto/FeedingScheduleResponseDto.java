package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;

/**
 * Dto that is returned as response when using methods related to the http services.
 * It contains information regarding feeding schedules that are visible to the client.
 */
public class FeedingScheduleResponseDto {

    private Long id;
    private String horseGuid;
    private String foodTypeName;
    private LocalDateTime feedingStartTime;
    private LocalDateTime feedingEndTime;
    private Long foodQuantityInKg;

    // Getters and setter methods

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

    public LocalDateTime getFeedingStartTime() {
        return feedingStartTime;
    }

    public void setFeedingStartTime(LocalDateTime feedingStartTime) {
        this.feedingStartTime = feedingStartTime;
    }

    public LocalDateTime getFeedingEndTime() {
        return feedingEndTime;
    }

    public void setFeedingEndTime(LocalDateTime feedingEndTime) {
        this.feedingEndTime = feedingEndTime;
    }

    public Long getFoodQuantityInKg() {
        return foodQuantityInKg;
    }

    public void setFoodQuantityInKg(Long foodQuantityInKg) {
        this.foodQuantityInKg = foodQuantityInKg;
    }
}
