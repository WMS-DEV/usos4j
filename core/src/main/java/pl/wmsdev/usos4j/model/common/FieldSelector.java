package pl.wmsdev.usos4j.model.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.wmsdev.usos4j.docs.ExcludeField;
import pl.wmsdev.usos4j.utils.StringUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldSelector {

    private String selectedFields;

    private static String extractFields(Class<? extends UsosObject> clazz) {
        Set<String> subfieldSelector = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(ExcludeField.class))
                .map(field -> {
                    String parameter = StringUtils.camelToSnakeCase(field.getName());
                    JsonAlias jsonAlias = field.getAnnotation(JsonAlias.class);
                    if(jsonAlias != null && jsonAlias.value().length > 0)
                        parameter = jsonAlias.value()[0];
                    Class<?> fieldType = field.getType();
                    if(Collection.class.isAssignableFrom(field.getType())) {
                        ParameterizedType listType = (ParameterizedType) field.getGenericType();
                        fieldType = (Class<?>) listType.getActualTypeArguments()[0];
                    }
                    if(UsosCommonObject.class.isAssignableFrom(fieldType))
                        parameter += "[" + extractFields((Class<UsosObject>) fieldType) + "]";
                    return parameter;
                })
                .collect(Collectors.toSet());

        return String.join("|", subfieldSelector);
    }

    public static FieldSelector fromRequest(Class<? extends UsosObject> usosObjectClass) {
        return new FieldSelector(extractFields(usosObjectClass));
    }

    public static FieldSelector fromFormattedString(String format, Class<? extends UsosObject> ...usosObjectClasses) {
        return new FieldSelector(String.format(format, Arrays.stream(usosObjectClasses).map(FieldSelector::extractFields)));
    }

    public FieldSelector addFields(String... fields) {
        selectedFields += (selectedFields.equals("") ? "" : "|") + String.join("|", fields);
        return this;
    }

    public FieldSelector removeFields(String... fields) {
        for(String field : fields) {
            int fieldStartIndex = selectedFields.indexOf(field) - (selectedFields.contains("|" + field) ? 1 : 0);
            if(fieldStartIndex == -1) break;
            int fieldEndIndex = selectedFields.indexOf(field) + field.length() - 1;
            if(selectedFields.length() > fieldEndIndex + 1 && selectedFields.charAt(fieldEndIndex + 1) == '[') {
                fieldEndIndex = selectedFields.indexOf("]", fieldStartIndex);
            }
            selectedFields = selectedFields.replace(selectedFields.substring(fieldStartIndex, fieldEndIndex + 1), "");
        }
        return this;
    }
}