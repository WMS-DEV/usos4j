package dev.wms.usos4j.model.user;

import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.Map;

public record UsosUserEmploymentFunction(UsosLocalizedString function,  UsosUserFacultyShortData faculty, Boolean isOfficial) {
    public record UsosUserFacultyShortData(String id, UsosLocalizedString name) {
    }
}
