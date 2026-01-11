package com.cinema.recommender.entity.enums;

public enum ShowtimeFormat {
    _2D("2D"),
    _3D("3D"),
    IMAX("IMAX"),
    _4DX("4DX");

    private final String value;

    ShowtimeFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ShowtimeFormat from(String input) {
        for (ShowtimeFormat format : values()) {
            if (format.value.equalsIgnoreCase(input)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Invalid showtime format: " + input);
    }
}