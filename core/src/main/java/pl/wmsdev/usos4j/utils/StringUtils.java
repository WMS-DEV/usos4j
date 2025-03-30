package pl.wmsdev.usos4j.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String camelToSnakeCase(String camelCase) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < camelCase.length(); i++) {
            if (Character.isUpperCase(camelCase.charAt(i))) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(camelCase.charAt(i)));
        }

        return sb.toString();
    }

}