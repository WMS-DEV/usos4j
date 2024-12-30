package pl.wmsdev.usos4j.model.geo;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosGeoRoomAttributes(
        String id,
        UsosLocalizedString description,
        String count
) {
}
