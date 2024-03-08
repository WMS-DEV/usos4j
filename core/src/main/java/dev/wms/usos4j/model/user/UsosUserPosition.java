package dev.wms.usos4j.model.user;

import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosUserPosition(Integer id, UsosLocalizedString name, UsosUserEmploymentGroup employmentGroup) {
}
