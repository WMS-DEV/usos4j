package pl.wmsdev.usos4j.model.geo;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGeoBuildings2Params(String buildingIds, UsosLanguage langpref) implements UsosParams {

    public static UsosGeoBuildings2ParamsBuilder builder(String... buildingIds) {
        String ids = FieldSelector.from(buildingIds).getSelectedFields();
        return new UsosGeoBuildings2ParamsBuilder().buildingIds(ids);
    }
}
