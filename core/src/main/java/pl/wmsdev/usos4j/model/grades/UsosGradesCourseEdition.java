package pl.wmsdev.usos4j.model.grades;

import pl.wmsdev.usos4j.model.common.UsosObject;

import java.util.Map;

public record UsosGradesCourseEdition(UsosGradesExam[] courseGrades, Map<String, UsosGradesExam[]> courseUnitsGrades) implements UsosObject {
}
