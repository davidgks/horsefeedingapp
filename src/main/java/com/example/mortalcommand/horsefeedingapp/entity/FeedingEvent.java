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

    @OneToMany
    @JoinColumn(name = "horse_id")
    private Set<Horse> horses;

    @OneToMany
    @JoinColumn(name = "feeding_schedule_id")
    private Set<FeedingSchedule> feedingSchedules;

    private LocalDateTime feedingTime;

    private boolean completed;

}
