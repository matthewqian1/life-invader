package com.example.lifeinvader.utils;

import com.example.lifeinvader.model.ActivityLevel;

import java.time.LocalDate;

public class CalorieUtils {
    public static int getRecommendedCalories(int heightCm, int weightKg, ActivityLevel activityLevel, int age) {
        return (int) (66.47 + (13.75 * weightKg) + (5.003 * heightCm) - (6.755 * age) * activityLevel.multiplier);
    }
}
