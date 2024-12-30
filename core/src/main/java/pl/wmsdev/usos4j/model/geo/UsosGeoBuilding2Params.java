package pl.wmsdev.usos4j.model.geo;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGeoBuilding2Params(UsosLanguage langpref, String buildingId) implements UsosParams {
    public static UsosGeoBuilding2ParamsBuilder builder(String buildingId) {
        return new UsosGeoBuilding2ParamsBuilder().buildingId(buildingId);
    }
}
