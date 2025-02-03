package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosDormroomTypesParam(String dormroomTypeIds) implements UsosParams {

    public static UsosDormroomTypesParamBuilder builder(String... dormroomTypeIds){
        return new UsosDormroomTypesParamBuilder().dormroomTypeIds(FieldSelector.from(dormroomTypeIds).getSelectedFields());
    }
}
