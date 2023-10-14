package com.example.mortalcommand.horsefeedingapp.dto;

/**
 * Dto that is provided as input to methods related to the http services.
 * It contains information regarding horses that are entered by the client.
 */
public class HorseDto {
    private String officialName;
    private String nickname;
    private String breed;
    private String ownerName;
    private String stableName;

    // Getter and setter methods
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStableName() {
        return stableName;
    }

    public void setStableName(String stableName) {
        this.stableName = stableName;
    }
}
