package pl.wmsdev.usos4j.model.plctests;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosPlacementTestParams(String id) implements UsosParams {

    public static UsosPlacementTestParams.UsosPlacementTestParamsBuilder builder(String id) {
        return new UsosPlacementTestParamsBuilder().id(id);
    }
}
