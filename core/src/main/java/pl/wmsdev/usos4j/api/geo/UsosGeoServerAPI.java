package pl.wmsdev.usos4j.api.geo;

import pl.wmsdev.usos4j.model.geo.*;

import java.util.List;
import java.util.Map;

public interface UsosGeoServerAPI {
    UsosGeoBuilding2 building2(UsosGeoBuilding2Params params);

    List<UsosGeoBuildingIndex> buildingIndex(UsosGeoBuildingIndexParams params);

    Map<String, UsosGeoBuilding2> buildings2(UsosGeoBuildings2Params params);

    UsosGeoRoom room(UsosGeoRoomParams params);

    Map<String, UsosGeoRoom> rooms(UsosGeoRoomsParams params);
}
