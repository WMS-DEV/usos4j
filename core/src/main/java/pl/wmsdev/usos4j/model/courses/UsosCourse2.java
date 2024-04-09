package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosCourse2(String id, UsosLocalizedString name, String homepageUrl, String profileUrl,
                          UsosLocalizedString description, UsosLocalizedString bibliography,
                          UsosLocalizedString learningOutcomes, UsosLocalizedString assessmentCriteria,
                          UsosLocalizedString practicalPlacement) implements UsosObject {
}
