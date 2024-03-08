package dev.wms.usos4j.model.courses;

import java.util.Collection;
import java.util.Map;

public record UsosCoursesUser(Map<String, Collection<UsosCourseEdition>> courseEditions) {
}
