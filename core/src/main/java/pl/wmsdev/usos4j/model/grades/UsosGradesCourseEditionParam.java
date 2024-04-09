package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradesCourseEditionParam(String courseId, String termId) implements UsosParams {

    public static UsosGradesCourseEditionParamBuilder builder(String courseId, String termId) {
        return new UsosGradesCourseEditionParamBuilder().courseId(courseId)
                .termId(termId);
    }
}
