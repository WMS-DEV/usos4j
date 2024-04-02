package pl.wmsdev.usos4j.utils;

public final class StringUtils {

    public static String camelToSnakeCase(String text) {
        return text.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    public static String arrayToPipelinedString(String requiredArgument, String... additionalArguments) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requiredArgument).append("|");
        for(String argument : additionalArguments) {
            stringBuilder.append(argument).append("|");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("|"));
        return stringBuilder.toString();
    }

}