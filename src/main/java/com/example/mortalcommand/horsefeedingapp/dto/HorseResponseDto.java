package com.example.mortalcommand.horsefeedingapp.dto;

import java.util.List;

/**
 * Dto that is returned as response when using methods related to the http services.
 * It contains information regarding horses that are visible to the client.
 */
public class HorseResponseDto {

    private Long id;
    private String guid;
    private String officialName;
    private String breed;
    private Long ownerId;
    private String ownerName;
    private Long stableId;
    private String stableName;
    private List<FeedingEventResponseDto> feedingEventResponseDtos;

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getStableId() {
        return stableId;
    }

    public void setStableId(Long stableId) {
        this.stableId = stableId;
    }

    public String getStableName() {
        return stableName;
    }

    public void setStableName(String stableName) {
        this.stableName = stableName;
    }

    public List<FeedingEventResponseDto> getFeedingEventResponseDtos() {
        return feedingEventResponseDtos;
    }

    public void setFeedingEventResponseDtos(List<FeedingEventResponseDto> feedingEventResponseDtos) {
        this.feedingEventResponseDtos = feedingEventResponseDtos;
    }
}
