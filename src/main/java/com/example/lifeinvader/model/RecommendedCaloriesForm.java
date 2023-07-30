package com.example.lifeinvader.model;

import lombok.Data;

@Data
public class RecommendedCaloriesForm {
    private int age;
    private int weightKg;
    private int heightCm;
    private ActivityLevel activityLevel;
}
