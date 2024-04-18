package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosUnit(String id, UsosLocalizedString courseName, String courseId, String termId,
                       String homepageUrl, String[] profileUrl, String classtypeId) implements UsosObject {
}
