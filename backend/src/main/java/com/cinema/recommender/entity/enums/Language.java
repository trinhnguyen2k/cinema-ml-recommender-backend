package com.cinema.recommender.entity.enums;

public enum Language {
    VIETNAMESE,
    ENGLISH,
    KOREAN,
    JAPANESE;

    public static Language from(String value) {
        try {
            return Language.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid showtime language");
        }
    }
}
