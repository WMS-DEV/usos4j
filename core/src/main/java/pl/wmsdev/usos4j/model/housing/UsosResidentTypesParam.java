package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosResidentTypesParam(String residentTypeIds) implements UsosParams {

    public static UsosResidentTypesParamBuilder builder(String... residentTypeIds) {
        return new UsosResidentTypesParamBuilder().residentTypeIds(FieldSelector.from(residentTypeIds).getSelectedFields());
    }
}