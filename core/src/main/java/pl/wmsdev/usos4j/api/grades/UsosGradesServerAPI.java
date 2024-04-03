package pl.wmsdev.usos4j.api.grades;

import pl.wmsdev.usos4j.model.grades.UsosGradeType;
import pl.wmsdev.usos4j.model.grades.UsosGradeTypeParam;
import pl.wmsdev.usos4j.model.grades.UsosGradeTypesParam;

import java.util.List;
import java.util.Map;

public interface UsosGradesServerAPI {

    UsosGradeType gradeType(UsosGradeTypeParam params);

    List<UsosGradeType> gradeTypeIndex();

    Map<String, UsosGradeType> gradeTypes(UsosGradeTypesParam param);
}
