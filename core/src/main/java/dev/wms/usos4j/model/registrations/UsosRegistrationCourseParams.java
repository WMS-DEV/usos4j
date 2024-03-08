package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegistrationCourseParams(String registrationId, String courseId, String termId) implements UsosParams {

    public static UsosRegistrationCourseParamsBuilder builder(String registrationId, String courseId, String termId) {
        return new UsosRegistrationCourseParamsBuilder().registrationId(registrationId).courseId(courseId).termId(termId);
    }

}
