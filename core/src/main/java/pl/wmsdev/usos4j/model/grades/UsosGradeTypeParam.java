package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradeTypeParam(String gradeTypeId) implements UsosParams {

    public static UsosGradeTypeParamBuilder builder(String gradeTypeId) {
        return new UsosGradeTypeParamBuilder().gradeTypeId(gradeTypeId);
    }
}
