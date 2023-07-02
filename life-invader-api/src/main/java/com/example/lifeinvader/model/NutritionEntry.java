package com.example.lifeinvader.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NutritionEntry {
    private String foodName;
    private int weightGrams;
    private LocalDate date;
}
