package com.example.lifeinvader.services;


import com.example.lifeinvader.model.NutritionEntry;
import com.example.lifeinvader.model.NutritionHistory;
import com.example.lifeinvader.model.NutritionItemData;
import com.example.lifeinvader.model.UserSession;
import com.example.lifeinvader.repo.NutritionHistoryRepo;
import com.example.lifeinvader.repo.NutritionItemRepo;
import com.example.lifeinvader.repo.SessionRepo;
import com.example.lifeinvader.utils.NutritionApiUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class NutritionEntryService {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private NutritionItemRepo nutritionItemRepo;
    @Autowired
    private NutritionHistoryRepo nutritionHistoryRepo;
    private final String nutritionSearchUrl = "https://api.api-ninjas.com/v1/nutrition";

    public void addEntry(String sessionToken, NutritionEntry entry) {
        sessionRepo.findById(sessionToken).ifPresent(s -> {
            String username = s.getUsername();
            nutritionHistoryRepo.findById(username).ifPresentOrElse(nutritionHistory -> {
                List<NutritionEntry> history = new ArrayList<>(nutritionHistory.getHistory());
                history.add(entry);
                nutritionHistory.setHistory(history);
                nutritionHistoryRepo.save(nutritionHistory);
            }, () -> {
                nutritionHistoryRepo.save(new NutritionHistory(username, Collections.singletonList(entry)));
            });
        });
    }

    public boolean validItem(String foodName) throws IOException {
        if (nutritionItemRepo.existsById(foodName)) {
            return true;
        }
        NutritionItemData result = NutritionApiUtils.searchItem(foodName);
        if (result == null) {
            return false;
        }
        nutritionItemRepo.save(result);
        return true;
    }
}
