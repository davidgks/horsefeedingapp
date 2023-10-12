package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;
import org.mapstruct.Mapping;

import java.util.Set;

@Entity
@Table(name = "horses")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhorse")
    private Long id;

//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "guid")
    private String guid;

    @Column(name = "official_name")
    private String officialName;

    @Column(name = "nickname")
    private String nickname;

    private String breed;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "stable_id", nullable = false)
    private Stable stable;

    @OneToMany(mappedBy = "horse")
    private Set<FeedingSchedule> feedingSchedules;

    @OneToMany(mappedBy = "horse")
    private Set<FeedingEvent> feedingEvents;

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Stable getStable() {
        return stable;
    }

    public void setStable(Stable stable) {
        this.stable = stable;
    }

    public Set<FeedingSchedule> getFeedingSchedules() {
        return feedingSchedules;
    }

    public void setFeedingSchedules(Set<FeedingSchedule> feedingSchedules) {
        this.feedingSchedules = feedingSchedules;
    }

    public Set<FeedingEvent> getFeedingEvents() {
        return feedingEvents;
    }

    public void setFeedingEvents(Set<FeedingEvent> feedingEvents) {
        this.feedingEvents = feedingEvents;
    }
}
