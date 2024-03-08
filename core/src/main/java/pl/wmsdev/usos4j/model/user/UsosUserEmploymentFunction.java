package pl.wmsdev.usos4j.model.user;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosUserEmploymentFunction(UsosLocalizedString function,  UsosUserFacultyShortData faculty, Boolean isOfficial) {
    public record UsosUserFacultyShortData(String id, UsosLocalizedString name) {
    }
}
