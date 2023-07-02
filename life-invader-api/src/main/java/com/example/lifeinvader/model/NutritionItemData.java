package com.example.lifeinvader.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("nutrition_items")
@Builder
public class NutritionItemData {
    @Id
    private String name;
    private int calories;
    private int servingSizeG;
    private int fatTotalG;
    private int fatSaturatedG;
    private int proteinG;
    private int sodiumMg;
    private int potassiumMg;
    private int cholesterolMg;
    private int carbohydratesTotalMg;
    private int fiberG;
    private int sugarG;
}
