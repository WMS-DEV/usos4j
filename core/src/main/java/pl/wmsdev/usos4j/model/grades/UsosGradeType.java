package pl.wmsdev.usos4j.model.grades;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosGradeType(String id, UsosLocalizedString name, UsosGradeDescription[] values) implements UsosObject {
}
