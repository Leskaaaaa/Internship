package com.leska.springapp.model;

import java.time.LocalDate;
import java.time.Month;

public enum Holidays {
    NEW_YEAR(LocalDate.of(2024, Month.JANUARY, 1));

    private final LocalDate date;

    Holidays(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate(int year) {
        return LocalDate.of(year, date.getMonth(), date.getDayOfMonth());
    }
}
