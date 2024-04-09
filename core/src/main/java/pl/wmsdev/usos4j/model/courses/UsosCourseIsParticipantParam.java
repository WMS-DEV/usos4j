package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseIsParticipantParam(String courseId, String termId) implements UsosParams {

    public static UsosCourseIsParticipantParamBuilder builder(String courseId, String termId) {
        return new UsosCourseIsParticipantParamBuilder().courseId(courseId).termId(termId);
    }
}
