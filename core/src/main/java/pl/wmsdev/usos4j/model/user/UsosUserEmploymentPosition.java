package pl.wmsdev.usos4j.model.user;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosUserEmploymentPosition(UsosUserPositionComponentData position, UsosUserPositionComponentData faculty) {
    public record UsosUserPositionComponentData(String id, UsosLocalizedString name) {
    }
}
