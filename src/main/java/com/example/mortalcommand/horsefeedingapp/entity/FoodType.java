package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "food_types")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfood_type")
    private Long id;

    private String foodName;

    @OneToMany(mappedBy = "foodType")
    private Set<FeedingSchedule> feedingScheduleSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Set<FeedingSchedule> getFeedingScheduleSet() {
        return feedingScheduleSet;
    }

    public void setFeedingScheduleSet(Set<FeedingSchedule> feedingScheduleSet) {
        this.feedingScheduleSet = feedingScheduleSet;
    }
}
