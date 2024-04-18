package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseUnitParam(String courseUnitId) implements UsosParams {
    public static UsosCourseUnitParamBuilder builder(String courseUnitId) {
        return new UsosCourseUnitParamBuilder().courseUnitId(courseUnitId);
    }
}
