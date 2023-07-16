package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.ConsumptionSnapshot;
import com.example.lifeinvader.model.NutritionEntry;
import com.example.lifeinvader.services.NutritionEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionEntryService nutritionEntryService;

    @PostMapping("/addEntry")
    public ResponseEntity<String> addEntry(@RequestHeader(name="Authorization") String token, @RequestBody NutritionEntry entry) {
        nutritionEntryService.addEntry(token, entry);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<String> search(String foodItem) throws IOException {
        return nutritionEntryService.validItem(foodItem) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/consumption/snapshot")
    public ResponseEntity<ConsumptionSnapshot> consumptionSnapshot(@RequestHeader(name="Authorization") String token) throws IOException {
        System.out.println("consumption");
        return ResponseEntity.ok(nutritionEntryService.getConsumptionSnapshot(token, ConsumptionSnapshot.Type.CALORIES, LocalDate.of(2023, 5, 1), LocalDate.now()));
    }
}
