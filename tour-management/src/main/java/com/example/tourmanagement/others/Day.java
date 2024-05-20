package com.example.tourmanagement.others;

import java.util.Arrays;

public class Day {
    public static String[] parseTourDescription(String description) {
        String[] days = description.split("\\s*(?=Day\\s*\\d+)");
        return Arrays.stream(days)
                .map(String::trim)        // Trim whitespace from each entry
                .filter(day -> !day.isEmpty())  // Filter out empty strings
                .toArray(String[]::new);
    }
}

