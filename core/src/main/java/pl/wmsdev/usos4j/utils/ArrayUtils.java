package pl.wmsdev.usos4j.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class ArrayUtils {

    private ArrayUtils() {}

    public static String joinArray(String delimiter, String... args) {
        return Arrays.stream(args).collect(Collectors.joining(delimiter));
    }
}
