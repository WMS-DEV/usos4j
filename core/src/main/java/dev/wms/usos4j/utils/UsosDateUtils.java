package dev.wms.usos4j.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public final class UsosDateUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String format(TemporalAccessor dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }

    public static String formatAsDate(TemporalAccessor date) {
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate toDate(String str) {
        return LocalDate.parse(str, DATE_FORMATTER);
    }

}
