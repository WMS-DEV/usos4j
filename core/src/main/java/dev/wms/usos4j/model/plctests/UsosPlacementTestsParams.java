package dev.wms.usos4j.model.plctests;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosPlacementTestsParams(String id) implements UsosParams {

    public static UsosPlacementTestsParams.UsosPlacementTestsParamsBuilder builder(String id) {
        return new UsosPlacementTestsParamsBuilder().id(id);
    }
}
