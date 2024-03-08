package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.docs.ExcludeField;
import dev.wms.usos4j.model.common.UsosCommonObject;
import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosGroup(Integer courseUnitId, Integer groupNumber, UsosLocalizedString classType,
                        String classTypeId, String groupUrl, String courseId, UsosLocalizedString courseName,
                        String courseHomepageUrl, String courseProfileUrl, Boolean courseIsCurrentlyConducted,
                        String courseFacId, String courseLangId, String termId, List<UsosGroupUser> lecturers,
                        List<UsosGroupUser> participants, @ExcludeField UsosGroupRole relationshipType) implements UsosCommonObject {}