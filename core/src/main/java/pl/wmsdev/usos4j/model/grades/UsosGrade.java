package pl.wmsdev.usos4j.model.grades;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosGrade(String valueSymbol, Boolean passes, UsosLocalizedString valueDescription,
                        String examId, String examSessionNumber, CountsIntoAverage countsIntoAverage,
                        String comment, String gradeTypeId, String dateModified, String dateAcquisition,
                        UsosGradeModificationAuthor modificationAuthor) implements UsosObject {
}
