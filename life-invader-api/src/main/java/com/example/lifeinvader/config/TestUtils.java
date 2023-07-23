package com.example.lifeinvader.config;

import com.example.lifeinvader.model.*;
import com.example.lifeinvader.repo.AccountRepo;
import com.example.lifeinvader.repo.NutritionHistoryRepo;
import com.example.lifeinvader.repo.NutritionItemRepo;
import com.example.lifeinvader.utils.CalorieUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class TestUtils {

    private static final String testUsername = "user1";
    private static final String testPassword = "password";

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    NutritionHistoryRepo nutritionHistoryRepo;

    @Autowired
    NutritionItemRepo nutritionItemRepo;

    @PostConstruct
    public void initTestUser() {
        log.info("Initialising test user details for {}", testUsername);
        accountRepo.save(new Account(testUsername, testPassword, null, null, 25, 84, 185, ActivityLevel.MODERATE, CalorieUtils.getRecommendedCalories(185, 84, ActivityLevel.MODERATE, 25)));
        nutritionItemRepo.save(NutritionItemData.builder()
                        .name("testBeans")
                        .calories(50)
                .build());
        nutritionItemRepo.save(NutritionItemData.builder()
                .name("testCookies")
                .calories(200)
                .build());
        nutritionItemRepo.save(NutritionItemData.builder()
                .name("testDonuts")
                .calories(400)
                .build());
        nutritionHistoryRepo.deleteAll();
        loadTestDetails(testUsername);

    }

    public void loadTestDetails(String username) {
        nutritionHistoryRepo.save(new NutritionHistory(username, List.of(
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(0))
                        .foodItem("testBeans")
                        .weightGrams(2500)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(1))
                        .foodItem("testBeans")
                        .weightGrams(3000)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(2))
                        .foodItem("testCookies")
                        .weightGrams(1000)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(3))
                        .foodItem("testDonuts")
                        .weightGrams(800)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(4))
                        .foodItem("testBeans")
                        .weightGrams(6000)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(5))
                        .foodItem("testCookies")
                        .weightGrams(2000)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.now().minusDays(6))
                        .foodItem("testDonuts")
                        .weightGrams(1200)
                        .build()
        )));
    }


}
