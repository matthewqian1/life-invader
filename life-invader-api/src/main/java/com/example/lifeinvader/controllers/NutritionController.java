package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.*;
import com.example.lifeinvader.services.NutritionEntryService;
import com.example.lifeinvader.utils.CalorieUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionEntryService nutritionEntryService;

    @PostMapping("/addEntry")
    public ResponseEntity<String> addEntry(@RequestHeader(name="Authorization") String token, @RequestBody NutritionEntry entry) throws IOException {
        nutritionEntryService.addEntry(token, entry);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getRecommendedCalories")
    public ResponseEntity<Integer> recommendCalories(@RequestBody RecommendedCaloriesForm form) throws IOException {
        return ResponseEntity.ok(CalorieUtils.getRecommendedCalories(form.getHeightCm(), form.getWeightKg(), form.getActivityLevel(), form.getAge()));
    }

    @GetMapping("/search/{foodItem}")
    public ResponseEntity<NutritionItemData> search(@PathVariable String foodItem) throws IOException {
        NutritionItemData result = nutritionEntryService.validItem(foodItem);
        return result == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/consumption/snapshot")
    public ResponseEntity<ConsumptionSnapshot> consumptionSnapshot(@RequestHeader(name="Authorization") String token) throws IOException {
        return ResponseEntity.ok(nutritionEntryService.getConsumptionSnapshot(token, ConsumptionSnapshot.Type.CALORIES, LocalDate.now().minusWeeks(1), LocalDate.now()));
    }

    @GetMapping("/consumption/breakdown")
    public ResponseEntity<List<NutritionEntry>> consumptionBreakdown(@RequestHeader(name="Authorization") String token, @RequestParam LocalDate date) throws IOException {
        return ResponseEntity.ok(nutritionEntryService.getBreakdown(token, date));
    }

}
