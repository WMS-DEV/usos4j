package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradesCourseEditionParams(String courseId, String termId) implements UsosParams {

    public static UsosGradesCourseEditionParamsBuilder builder(String courseId, String termId) {
        return new UsosGradesCourseEditionParamsBuilder().courseId(courseId)
                .termId(termId);
    }
}
