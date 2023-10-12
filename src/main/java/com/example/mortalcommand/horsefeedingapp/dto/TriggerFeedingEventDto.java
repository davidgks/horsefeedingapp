package com.example.mortalcommand.horsefeedingapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TriggerFeedingEventDto {

    private String horseGuid;
    private LocalDateTime dateTimeStamp;

    // Getter and setter methods
    public String getHorseGuid() {
        return horseGuid;
    }

    public void setHorseGuid(String horseGuid) {
        this.horseGuid = horseGuid;
    }

    public LocalDateTime getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }
}
