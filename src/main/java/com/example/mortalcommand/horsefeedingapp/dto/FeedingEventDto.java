package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeedingEventDto {

    private Long id;
    private String horseGuid;
//    private FeedingScheduleResponseDto feedingScheduleResponseDto;
    private Long feedingScheduleId;
    private LocalTime feedingStartTime;
    private LocalTime feedingEndTime;
    private LocalDateTime feedingTime;
    private boolean completed;

    // Getters and setters
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

    public Long getFeedingScheduleId() {
        return feedingScheduleId;
    }

    public void setFeedingScheduleId(Long feedingScheduleId) {
        this.feedingScheduleId = feedingScheduleId;
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
