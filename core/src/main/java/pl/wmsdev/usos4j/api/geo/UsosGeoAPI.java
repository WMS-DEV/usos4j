package pl.wmsdev.usos4j.api.geo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.geo.*;

import java.util.List;
import java.util.Map;

public class UsosGeoAPI extends UsosUserAPIDefinition implements UsosGeoServerAPI {

    public UsosGeoAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    public UsosGeoBuilding2 building2(UsosGeoBuilding2Params params) {
        return request(requestFactory.get("services/geo/building2",
                        params, FieldSelector.fromRequest(UsosGeoBuilding2.class)),
                UsosGeoBuilding2.class);
    }

    @Override
    public List<UsosGeoBuildingIndex> buildingIndex(UsosGeoBuildingIndexParams params) {
        return request(requestFactory.get("services/geo/building_index",
                        params, FieldSelector.fromRequest(UsosGeoBuildingIndex.class)),
                new TypeReference<>() {
                });
    }

    @Override
    public Map<String, UsosGeoBuilding2> buildings2(UsosGeoBuildings2Params params) {
        return request(requestFactory.get("services/geo/buildings2",
                        params, FieldSelector.fromRequest(UsosGeoBuilding2.class)),
                new TypeReference<>() {
                });
    }

    @Override
    public UsosGeoRoom room(UsosGeoRoomParams params) {
        return requestWithAccessToken(requestFactory.get("services/geo/room",
                params, FieldSelector.fromRequest(UsosGeoRoom.class)), UsosGeoRoom.class);
    }

    @Override
    public Map<String, UsosGeoRoom> rooms(UsosGeoRoomsParams params) {
        return requestWithAccessToken(requestFactory.get("services/geo/rooms",
                        params, FieldSelector.fromRequest(UsosGeoRoom.class)),
                new TypeReference<>() {
                });
    }

}
