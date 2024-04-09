package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseParam(String courseId) implements UsosParams {

    public static UsosCourseParamBuilder builder(String courseId) {
        return new UsosCourseParamBuilder().courseId(courseId);
    }
}
