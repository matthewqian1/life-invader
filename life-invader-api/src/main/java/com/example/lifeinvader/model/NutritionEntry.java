package com.example.lifeinvader.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NutritionEntry {
    private String foodName;
    private int weightGrams;
    private LocalDate date;
}
