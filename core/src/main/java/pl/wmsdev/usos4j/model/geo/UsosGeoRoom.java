package pl.wmsdev.usos4j.model.geo;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;

public record UsosGeoRoom(
        String id,
        String number,
        UsosGeoBuilding2 building,
        String type,
        String capacity,
        List<UsosGeoRoomAttributes> attributes,
        List<UsosUser> staff
) implements UsosObject {
}
