package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosFacultyShort(String id, UsosLocalizedString name, String profileUrl) implements UsosCommonObject {}