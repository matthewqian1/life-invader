package com.example.lifeinvader.repo;

import com.example.lifeinvader.model.NutritionItemData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NutritionItemRepo extends MongoRepository<NutritionItemData, String> {
}
