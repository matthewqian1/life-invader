package com.example.lifeinvader.services;


import com.example.lifeinvader.model.*;
import com.example.lifeinvader.repo.AccountRepo;
import com.example.lifeinvader.repo.NutritionHistoryRepo;
import com.example.lifeinvader.repo.NutritionItemRepo;
import com.example.lifeinvader.repo.SessionRepo;
import com.example.lifeinvader.utils.NutritionApiUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.lifeinvader.model.ConsumptionSnapshot.*;

@Slf4j
@Service
public class NutritionEntryService {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private NutritionItemRepo nutritionItemRepo;
    @Autowired
    private NutritionHistoryRepo nutritionHistoryRepo;

    @Autowired
    private AccountRepo accountRepo;
    private final String nutritionSearchUrl = "https://api.api-ninjas.com/v1/nutrition";

    public void addEntry(String sessionToken, NutritionEntry entry) {
        sessionRepo.findById(sessionToken).ifPresent(s -> {
            String username = s.getUsername();
            nutritionHistoryRepo.findById(username).ifPresentOrElse(nutritionHistory -> {
                List<NutritionEntry> history = new ArrayList<>(nutritionHistory.getHistory());
                history.add(entry);
                nutritionHistory.setHistory(history);
                nutritionHistoryRepo.save(nutritionHistory);
                log.info("food entry added to existing record!");
            }, () -> {
                nutritionHistoryRepo.save(new NutritionHistory(username, Collections.singletonList(entry)));
                log.info("New record created!");
            });
        });
    }

    public NutritionHistory getHistory(String sessionToken) {
        return nutritionHistoryRepo.findById(sessionRepo.findById(sessionToken).get().getUsername()).get();
    }

    public List<NutritionEntry> getBreakdown(String sessionToken, LocalDate date) {
        return nutritionHistoryRepo.findById(sessionRepo.findById(sessionToken).get().getUsername()).get()
                .getHistory()
                .stream()
                .filter(e -> e.getDate().equals(date))
                .sorted(Comparator.comparing(NutritionEntry::getDate))
                .collect(Collectors.toList());

    }


    public ConsumptionSnapshot getConsumptionSnapshot(String sessionToken, ConsumptionSnapshot.Type type, LocalDate start, LocalDate end) {
        String username = sessionRepo.findById(sessionToken).get().getUsername();
        int dailyCalorieGoal = accountRepo.findById(username).get().getDailyCalorieGoal();
        ConsumptionSnapshot snapshot = ConsumptionSnapshot.builder()
                .type(type)
                .history(new ArrayList<>())
                .build();
        if (!nutritionHistoryRepo.existsById(username)) {
            return snapshot;
        }
        NutritionHistory history = nutritionHistoryRepo.findById(username).get();
        List<NutritionEntry> filtered = history.getHistory()
                .stream()
                .sorted(Comparator.comparing(NutritionEntry::getDate))
                .filter(h -> (!h.getDate().isBefore(start) && !h.getDate().isAfter(end)))
                .toList();

        if (filtered.isEmpty()) {
            return snapshot;
        }
        Consumption currentConsumption = toConsumption(type, filtered.get(0), dailyCalorieGoal);
        if (filtered.size() == 1) {
            return ConsumptionSnapshot.builder()
                    .history(Collections.singletonList(currentConsumption))
                    .type(type)
                    .build();
        }
        List<Consumption> consumptionList = new ArrayList<>();
        for (int i = 1; i < filtered.size(); i++) {
            Consumption consumption = toConsumption(type, filtered.get(i), dailyCalorieGoal);
            if (currentConsumption.getDate().equals(consumption.getDate())) {
                currentConsumption.setUnit(currentConsumption.getUnit() + consumption.getUnit());
            } else {
                consumptionList.add(currentConsumption);
                currentConsumption = consumption;
            }
        }
        consumptionList.add(currentConsumption);
        return ConsumptionSnapshot.builder()
                .type(type)
                .history(consumptionList)
                .build();
    }

    private Consumption toConsumption(Type type, NutritionEntry entry, int dailyCalorieGoal) {
        NutritionItemData item  = nutritionItemRepo.findById(entry.getFoodItem()).get();
        int units = 0;
        switch (type) {
            case CALORIES -> {
                units =(entry.getWeightGrams() * item.getCalories()) / 100;
            }
        }

        return new Consumption(units, dailyCalorieGoal, entry.getDate());
    }


    public NutritionItemData validItem(String foodName) throws IOException {
        if (nutritionItemRepo.existsById(foodName)) {
            log.info("{} exists in repo, returning cached result", foodName);
            return nutritionItemRepo.findById(foodName).get();
        }
        NutritionItemData result = NutritionApiUtils.searchItem(foodName);
        if (result == null) {
            log.warn("{} not a valid food item", foodName);
            return null;
        }
        nutritionItemRepo.save(result);
        log.info("new food item {} added to database", foodName);
        return result;
    }

}
