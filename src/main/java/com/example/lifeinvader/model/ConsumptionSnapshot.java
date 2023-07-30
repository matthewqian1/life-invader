package com.example.lifeinvader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ConsumptionSnapshot {

    Type type;
    List<Consumption> history;

    public enum Type {
        CALORIES
    }

    @Data
    @AllArgsConstructor
    public static class Consumption {
        int unit;
        int dailyCalorieGoal;
        LocalDate date;
    }
}
