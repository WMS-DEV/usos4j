package pl.wmsdev.usos4j.model.user;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosUserPosition(Integer id, UsosLocalizedString name, UsosUserEmploymentGroup employmentGroup) {
}
