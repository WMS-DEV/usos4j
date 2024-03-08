package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosTokenRegistrationCourse(String registrationId, String courseId, String termId, String status,
                                          String limits, String tstCode, String lowerLimit, String startDate)
                                        implements UsosObject {
}
