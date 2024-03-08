package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosCourseUnit(String id, String homepageUrl, String profileUrl, UsosLocalizedString learningOutcomes,
                             UsosLocalizedString assessmentCriteria, UsosLocalizedString topics, UsosLocalizedString teachingMethods,
                             UsosLocalizedString bibliography) implements UsosCommonObject {}