package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTokenRegistrationCourseParams(String registrationId, String courseId,
                                                String termId) implements UsosParams {

    public static UsosTokenRegistrationCourseParamsBuilder builder(String registrationId, String courseId, String termId) {
        return new UsosTokenRegistrationCourseParamsBuilder().registrationId(registrationId).courseId(courseId)
                .termId(termId);
    }

}
