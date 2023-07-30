package com.example.lifeinvader.repo;

import com.example.lifeinvader.model.NutritionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NutritionHistoryRepo extends MongoRepository<NutritionHistory, String> {
}
