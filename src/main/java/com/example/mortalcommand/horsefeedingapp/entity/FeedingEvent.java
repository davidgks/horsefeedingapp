package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "feeding_events")
public class FeedingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfeeding_event")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "feeding_schedule_id")
    private FeedingSchedule feedingSchedule;

    private LocalDateTime feedingTime;

    private boolean completed;

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

    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
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
