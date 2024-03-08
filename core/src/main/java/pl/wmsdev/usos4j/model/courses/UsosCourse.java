package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosCourse(String id, UsosLocalizedString name) implements UsosCommonObject {}
