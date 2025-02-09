package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosDormroomTypeParam(String dormroomTypeId) implements UsosParams {

    public static UsosDormroomTypeParamBuilder builder(String dormroomTypeId) {
        return new UsosDormroomTypeParamBuilder().dormroomTypeId(dormroomTypeId);
    }
}
