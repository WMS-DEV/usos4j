package pl.wmsdev.usos4j.model.housing;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosResidentType(String id, UsosLocalizedString name) implements UsosObject {}
