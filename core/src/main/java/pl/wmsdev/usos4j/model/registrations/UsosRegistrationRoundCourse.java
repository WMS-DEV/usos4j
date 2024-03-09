package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.courses.UsosCourse;

public record UsosRegistrationRoundCourse(UsosCourse course, String termId, UsosRegistrationRoundCourseStatus status,
                                          int limits, boolean isLinkageRequired, int registrationsCount,
                                          UsosUserRegistrationStatus userRegistrationStatus, Integer userChoiceNumber,
                                          boolean isRegistrationValid) implements UsosObject {}
