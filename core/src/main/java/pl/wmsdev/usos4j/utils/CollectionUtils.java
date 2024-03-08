package pl.wmsdev.usos4j.utils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionUtils {

    public static <K, V> Map<K, V> mergeMaps(Map<K, V> first, Map<K, V> second) {
        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
