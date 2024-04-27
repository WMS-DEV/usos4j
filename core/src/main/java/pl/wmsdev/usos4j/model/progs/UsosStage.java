package pl.wmsdev.usos4j.model.progs;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosStage(String id, UsosLocalizedString name) implements UsosObject {
}
