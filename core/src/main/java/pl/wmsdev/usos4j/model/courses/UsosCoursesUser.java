package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.terms.UsosTerm;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public record UsosCoursesUser(Map<String, Collection<UsosCourseEdition>> courseEditions,
                              List<UsosTerm> terms) implements UsosObject {
}
