package com.example.lifeinvader.controllers;

import com.example.lifeinvader.model.ConsumptionSnapshot;
import com.example.lifeinvader.model.NutritionEntry;
import com.example.lifeinvader.model.NutritionItemData;
import com.example.lifeinvader.services.NutritionEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

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

    @GetMapping("/search/{foodItem}")
    public ResponseEntity<NutritionItemData> search(@PathVariable String foodItem) throws IOException {
        NutritionItemData result = nutritionEntryService.validItem(foodItem);
        return result == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(result);
    }

    @GetMapping("/consumption/snapshot")
    public ResponseEntity<ConsumptionSnapshot> consumptionSnapshot(@RequestHeader(name="Authorization") String token) throws IOException {
        System.out.println("consumption");
        return ResponseEntity.ok(nutritionEntryService.getConsumptionSnapshot(token, ConsumptionSnapshot.Type.CALORIES, LocalDate.of(2023, 5, 1), LocalDate.now()));
    }


}
