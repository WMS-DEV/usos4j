package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosUnitsParam(String unitIds) implements UsosParams {

    public static UsosUnitsParamBuilder builder(String... unitIds) {
        String ids = FieldSelector.from(unitIds).getSelectedFields();
        return new UsosUnitsParamBuilder().unitIds(ids);
    }
}
