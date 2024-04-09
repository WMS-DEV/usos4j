package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.terms.UsosTerm;

public record UsosCourseEdition2(UsosCourse2 course, UsosTerm term, String homepageUrl,
                                 UsosLocalizedString description, UsosLocalizedString bibliography,
                                 UsosLocalizedString notes) implements UsosObject {
}
