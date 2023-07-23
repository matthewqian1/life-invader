package com.example.lifeinvader.model;

public enum ActivityLevel {
    LOW("Low", 1.375),
    MODERATE("Moderate", 1.55),
    HIGH("High", 1.725);

    public final String name;
    public final double multiplier;

    ActivityLevel(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }
}
