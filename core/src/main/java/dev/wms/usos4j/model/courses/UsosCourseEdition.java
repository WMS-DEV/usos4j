package dev.wms.usos4j.model.courses;

import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosCourseEdition(String courseId, UsosLocalizedString courseName, String termId, List<Object> userGroups) {
}
