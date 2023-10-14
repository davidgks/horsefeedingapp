package com.example.mortalcommand.horsefeedingapp.dto;

/**
 * Dto that is returned as response when using methods related to the http services.
 * It contains information regarding stables that are visible to the client.
 */
public class StableResponseDto {

    private Long id;
    private String stableName;
    private String location;

    // Getter and setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStableName() {
        return stableName;
    }

    public void setStableName(String stableName) {
        this.stableName = stableName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
