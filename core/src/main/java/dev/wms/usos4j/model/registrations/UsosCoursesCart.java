package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.courses.UsosCourse;

import java.util.List;

public record UsosCoursesCart (List<Object> links, UsosCourse course, UsosRegistrationTerm term,
                               UsosUserRegistrationsStatus userRegistrationStatus, Boolean isRegistrationValid,
                               Integer choiceNumber, Integer limits, Boolean isLinkageRequired,
                               Integer registrations_count, UsosRegistrationStatus registrationStatus,
                               String activeRegistrationRoundId) implements UsosObject {
}
