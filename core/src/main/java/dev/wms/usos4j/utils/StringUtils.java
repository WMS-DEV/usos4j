package dev.wms.usos4j.utils;

public final class StringUtils {

    public static String camelToSnakeCase(String text) {
        return text.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

}