package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseEditionParam(String courseId, String termId) implements UsosParams {

    public static UsosCourseEditionParamBuilder builder(String courseId, String termId) {
        return new UsosCourseEditionParamBuilder().courseId(courseId).termId(termId);
    }
}
