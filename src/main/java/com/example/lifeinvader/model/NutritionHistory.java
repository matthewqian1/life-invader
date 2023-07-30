package com.example.lifeinvader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("nutrition_history")
@Data
@AllArgsConstructor
public class NutritionHistory {
    @Id
    private String username;
    private List<NutritionEntry> history;
}
