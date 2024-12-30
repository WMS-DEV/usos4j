package pl.wmsdev.usos4j.model.geo;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGeoRoomParams(String roomId) implements UsosParams {
    public static UsosGeoRoomParamsBuilder builder(String roomId) {
        return new UsosGeoRoomParamsBuilder().roomId(roomId);
    }
}
