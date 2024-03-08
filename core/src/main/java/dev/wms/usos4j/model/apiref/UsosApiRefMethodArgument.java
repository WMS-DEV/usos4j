package dev.wms.usos4j.model.apiref;

public record UsosApiRefMethodArgument(String name, boolean isRequired, boolean isDeprecated, String defaultValue,
                                       String description, String type) {
}
