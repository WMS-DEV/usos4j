package pl.wmsdev.usos4j.model.grades;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosGradeDescription(String symbol, boolean passes, String decimalValue,
                                   UsosLocalizedString name, Integer orderKey) {
}
