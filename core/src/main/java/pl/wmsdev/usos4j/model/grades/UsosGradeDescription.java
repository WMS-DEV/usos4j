package pl.wmsdev.usos4j.model.grades;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosGradeDescription(String symbol, Boolean passes, String decimalValue,
                                   UsosLocalizedString name, Integer orderKey) {
}
