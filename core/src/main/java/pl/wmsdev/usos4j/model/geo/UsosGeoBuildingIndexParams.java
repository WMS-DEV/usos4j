package pl.wmsdev.usos4j.model.geo;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGeoBuildingIndexParams(String facIds) implements UsosParams {

    public static UsosGeoBuildingIndexParamsBuilder builder() {
        return new UsosGeoBuildingIndexParamsBuilder();
    }

    public static class UsosGeoBuildingIndexParamsBuilder {
        public UsosGeoBuildingIndexParamsBuilder facIds(String... facIds) {
            this.facIds = FieldSelector.from(facIds).getSelectedFields();
            return this;
        }
    }
}
