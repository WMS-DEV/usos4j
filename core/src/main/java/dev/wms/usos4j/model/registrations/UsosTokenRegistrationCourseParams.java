package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTokenRegistrationCourseParams (String registrationId, String courseId, String termId) implements UsosParams {

    public static UsosTokenRegistrationCourseParamsBuilder builder(String registrationId, String courseId, String termId) {
        return new UsosTokenRegistrationCourseParamsBuilder().registrationId(registrationId).courseId(courseId).termId(termId);
    }

}
