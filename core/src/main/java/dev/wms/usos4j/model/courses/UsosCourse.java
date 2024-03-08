package dev.wms.usos4j.model.courses;

import dev.wms.usos4j.model.common.UsosCommonObject;
import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosCourse(String id, UsosLocalizedString name) implements UsosCommonObject {}
