package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosDormitoriesParam (String dormitoryIds) implements UsosParams {

    public static UsosDormitoriesParamBuilder builder(String... dormitoryIds){
        return new UsosDormitoriesParamBuilder().dormitoryIds(
                FieldSelector.from(dormitoryIds).getSelectedFields());
    }
}
