package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.courses.UsosCourse;

public record UsosRegistrationRoundCourse(UsosCourse course, String termId, UsosRegistrationRoundCourseStatus status,
                                          Integer limits, Boolean isLinkageRequired, Integer registrationsCount,
                                          UsosUserRegistrationStatus userRegistrationStatus, Integer userChoiceNumber,
                                          Boolean isRegistrationValid) implements UsosObject {}
