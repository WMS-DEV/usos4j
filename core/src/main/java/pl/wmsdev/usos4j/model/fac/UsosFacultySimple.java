package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.List;

public record UsosFacultySimple(String id, UsosLocalizedString name) implements UsosObject {}