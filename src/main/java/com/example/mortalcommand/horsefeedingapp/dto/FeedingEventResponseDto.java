package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeedingEventResponseDto {

    private Long id;
    private String horseGuid;
    private LocalDateTime feedingStartTime;
    private LocalDateTime feedingEndTime;
    private LocalDateTime feedingTime;
    private boolean completed;

    // Getter and Setter methods
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
