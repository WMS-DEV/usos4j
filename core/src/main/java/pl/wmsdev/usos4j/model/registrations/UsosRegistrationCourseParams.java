package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosRegistrationCourseParams(String registrationId, String courseId,
                                           String termId) implements UsosParams {

    public static UsosRegistrationCourseParamsBuilder builder(String registrationId, String courseId, String termId) {
        return new UsosRegistrationCourseParamsBuilder().registrationId(registrationId).courseId(courseId)
                .termId(termId);
    }

}
