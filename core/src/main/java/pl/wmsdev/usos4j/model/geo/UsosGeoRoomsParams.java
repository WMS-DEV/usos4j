package pl.wmsdev.usos4j.model.geo;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGeoRoomsParams(String roomIds) implements UsosParams {
    public static UsosGeoRoomsParams.UsosGeoRoomsParamsBuilder builder(String... roomIds) {
        String ids = FieldSelector.from(roomIds).getSelectedFields();
        return new UsosGeoRoomsParamsBuilder().roomIds(ids);
    }
}
