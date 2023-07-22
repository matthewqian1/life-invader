package com.example.lifeinvader.config;

import com.example.lifeinvader.model.*;
import com.example.lifeinvader.repo.AccountRepo;
import com.example.lifeinvader.repo.NutritionHistoryRepo;
import com.example.lifeinvader.repo.NutritionItemRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class TestUserConfig {

    private static final String testUsername = "user1";
    private static final String testPassword = "password";

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    NutritionHistoryRepo nutritionHistoryRepo;

    @Autowired
    NutritionItemRepo nutritionItemRepo;

    @PostConstruct
    public void init() {
        log.info("Initialising test user details for {}", testUsername);
        accountRepo.save(new Account(testUsername, testPassword, null, null));
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
        nutritionHistoryRepo.save(new NutritionHistory(testUsername, List.of(
                NutritionEntry.builder()
                        .date(LocalDate.of(2023, 6, 6))
                        .foodItem("testBeans")
                        .weightGrams(100)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.of(2023, 6, 5))
                        .foodItem("testCookies")
                        .weightGrams(100)
                        .build(),
                NutritionEntry.builder()
                        .date(LocalDate.of(2023, 6, 7))
                        .foodItem("testDonuts")
                        .weightGrams(100)
                        .build()
        )));

    }


}
