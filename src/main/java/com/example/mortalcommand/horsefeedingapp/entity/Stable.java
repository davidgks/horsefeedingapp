package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

/**
 * Entity that represents a stable
 */
@Entity
@Table(name = "stables")
public class Stable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstable")
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
