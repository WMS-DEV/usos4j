package dev.wms.usos4j.model.common;

import dev.wms.usos4j.utils.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public interface UsosParams {

    default Map<String, Collection<String>> getParams() {
        return getParams(null);
    }

    default Map<String, Collection<String>> getParams(FieldSelector fieldSelector) {
        Map<String, Collection<String>> params = Arrays.stream(getClass().getDeclaredFields())
                .filter(this::nonNullField)
                .collect(Collectors.toMap(field -> StringUtils.camelToSnakeCase(field.getName()), this::extractParam));
        if(fieldSelector != null) params.put("fields", List.of(fieldSelector.getSelectedFields()));
        return params;
    }

    private Collection<String> extractParam(Field field) {
        try {
            Collection<String> param;
            if(Collection.class.isAssignableFrom(field.getDeclaringClass())) {
                Collection<?> objectParam = (Collection<?>) field.get(this);
                param = objectParam.stream().map(object -> object.getClass().isEnum() ? object.toString().toLowerCase() : object.toString()).toList();
            } else if(field.getDeclaringClass().isArray()) {
                Object[] objectParam = toObjectArray(field.get(this));
                param = Arrays.stream(objectParam).map(object -> object.getClass().isEnum() ? object.toString().toLowerCase() : object.toString()).toList();
            } else {
                Object objectParam = field.get(this);
                param = List.of(objectParam.getClass().isEnum() ? objectParam.toString().toLowerCase() : objectParam.toString());
            }
            return param;
        } catch (IllegalAccessException e) {
            return Collections.emptyList();
        }
    }

    private boolean nonNullField(Field field) {
        field.setAccessible(true);
        try {
            return field.get(this) != null;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    private static Object[] toObjectArray(Object arrayObject) {
        assert arrayObject.getClass().isArray() : "Object has to be an array";
        int length = Array.getLength(arrayObject);
        Object[] array = new Object[length];
        for(int i = 0; i < length; i++)
            array[i] = Array.get(arrayObject, i);
        return array;
    }

}
