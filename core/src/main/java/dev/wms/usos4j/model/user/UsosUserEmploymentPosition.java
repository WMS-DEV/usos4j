package dev.wms.usos4j.model.user;

import dev.wms.usos4j.model.common.UsosLocalizedString;

import java.util.Map;

public record UsosUserEmploymentPosition(UsosUserPositionComponentData position, UsosUserPositionComponentData faculty) {
    public record UsosUserPositionComponentData(String id, UsosLocalizedString name) {
    }
}
