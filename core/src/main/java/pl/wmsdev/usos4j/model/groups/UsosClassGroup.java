package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.courses.UsosCourseUnit;
import pl.wmsdev.usos4j.model.user.UsosUserShort;

import java.util.List;

public record UsosClassGroup(Integer courseUnitId, Integer number, String url,
                             UsosLocalizedString description, UsosLocalizedString literature,
                             UsosCourseUnit courseUnit, List<UsosUserShort> lecturers,
                             List<UsosUserShort> participants) implements UsosObject {}
