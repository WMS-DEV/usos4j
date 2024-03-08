package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosRegistrationCourse(String registrationId, String courseId, String termId,
                                     String status, String limits, String wwwInstance) implements UsosObject {
}
