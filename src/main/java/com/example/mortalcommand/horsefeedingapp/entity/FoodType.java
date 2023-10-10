package com.example.mortalcommand.horsefeedingapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food_types")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfood_type")
    private Long id;

    private String foodName;

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
}
