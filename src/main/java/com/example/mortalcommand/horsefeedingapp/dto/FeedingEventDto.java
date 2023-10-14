package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeedingEventDto {

    private String horseGuid;
    private Long feedingScheduleId;
    private LocalDateTime feedingStartTime;
    private LocalDateTime feedingEndTime;
    private LocalDateTime feedingTime;
    private boolean completed;

    // Getters and setters
    public String getHorseGuid() {
        return horseGuid;
    }

    public void setHorseGuid(String horseGuid) {
        this.horseGuid = horseGuid;
    }

    public Long getFeedingScheduleId() {
        return feedingScheduleId;
    }

    public void setFeedingScheduleId(Long feedingScheduleId) {
        this.feedingScheduleId = feedingScheduleId;
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

    public LocalDateTime getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(LocalDateTime feedingTime) {
        this.feedingTime = feedingTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
