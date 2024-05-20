package pl.wmsdev.usos4j.model.apiref;

public record UsosApiRefMethodArgument(String name, Boolean isRequired, Boolean isDeprecated, String defaultValue,
                                       String description, String type) {
}
