package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosUnitParam(String unitId) implements UsosParams {

    public static UsosUnitParamBuilder builder(String unitId) {
        return new UsosUnitParamBuilder().unitId(unitId);
    }
}
