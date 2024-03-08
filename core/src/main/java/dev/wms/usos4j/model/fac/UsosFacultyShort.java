package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosCommonObject;
import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosFacultyShort(String id, UsosLocalizedString name, String profileUrl) implements UsosCommonObject {}