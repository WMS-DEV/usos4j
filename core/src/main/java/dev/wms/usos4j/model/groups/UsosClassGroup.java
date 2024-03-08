package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.courses.UsosCourse;
import dev.wms.usos4j.model.courses.UsosCourseUnit;
import dev.wms.usos4j.model.user.UsosUserShort;

import java.util.List;

public record UsosClassGroup(Integer courseUnitId, Integer number, String url,
                             UsosLocalizedString description, UsosLocalizedString literature,
                             UsosCourseUnit courseUnit, List<UsosUserShort> lecturers,
                             List<UsosUserShort> participants) implements UsosObject {}
