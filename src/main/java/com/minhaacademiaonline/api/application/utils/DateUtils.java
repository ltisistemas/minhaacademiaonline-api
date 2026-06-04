package com.minhaacademiaonline.api.application.utils;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static LocalDate getNextBusinessDay() {
        return getNextBusinessDay(LocalDate.now());
    }
    public static LocalDate getNextBusinessDay(LocalDate date) {
        LocalDate nextDay = date.plusDays(1);

        while (isWeekend(nextDay)) {
            nextDay = nextDay.plusDays(1);
        }

        return nextDay;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
