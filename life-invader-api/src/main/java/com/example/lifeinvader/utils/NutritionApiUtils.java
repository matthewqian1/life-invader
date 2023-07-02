package com.example.lifeinvader.utils;

import com.example.lifeinvader.model.NutritionItemData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NutritionApiUtils {
    private static final String nutritionSearchUrl = "https://api.api-ninjas.com/v1/nutrition";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static NutritionItemData searchItem(String foodName) throws IOException {
        URL url = new URL(String.format("%s/query=%s", nutritionSearchUrl, foodName));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", "BmkEi8jF05eXjSoaizJOCQ==V0ddaLo63EAS7aTF");

        InputStream responseStream = connection.getInputStream();
        JsonNode root = mapper.readTree(responseStream);
        if (root.size() == 0) {
            return null;
        }

        JsonNode data = root.get(0);
        String name = data.get("name").asText();
        int calories = data.get("calories").asInt();
        int servingSizeG = data.get("serving_size_g").asInt();
        int fatTotalG = data.get("fat_total_g").asInt();
        int fatSaturatedG = data.get("fat_saturated_g").asInt();
        int proteinG = data.get("protein_g").asInt();
        int sodiumMg = data.get("sodium_mg").asInt();
        int potassiumMg = data.get("potassium_mg").asInt();
        int cholesterolMg = data.get("cholesterol_mg").asInt();
        int carbohydratesTotalMg = data.get("carbohydrates_total_g").asInt();
        int fiberG = data.get("fiber_g").asInt();
        int sugarG = data.get("sugar_g").asInt();
        return NutritionItemData.builder()
                .name(name)
                .calories(calories)
                .servingSizeG(servingSizeG)
                .fatTotalG(fatTotalG)
                .fatSaturatedG(fatSaturatedG)
                .proteinG(proteinG)
                .sodiumMg(sodiumMg)
                .potassiumMg(potassiumMg)
                .cholesterolMg(cholesterolMg)
                .carbohydratesTotalMg(carbohydratesTotalMg)
                .fiberG(fiberG)
                .sugarG(sugarG)
                .build();

    }
}
