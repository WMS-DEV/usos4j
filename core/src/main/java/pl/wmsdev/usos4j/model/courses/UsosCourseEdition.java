package pl.wmsdev.usos4j.model.courses;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.groups.UsosGroup;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;

public record UsosCourseEdition(String courseId, UsosLocalizedString courseName,
                                String termId, List<UsosGroup> userGroups) implements UsosObject {
}
