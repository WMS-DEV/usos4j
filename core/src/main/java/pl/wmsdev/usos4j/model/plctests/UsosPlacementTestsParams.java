package pl.wmsdev.usos4j.model.plctests;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosPlacementTestsParams(String id) implements UsosParams {

    public static UsosPlacementTestsParams.UsosPlacementTestsParamsBuilder builder(String id) {
        return new UsosPlacementTestsParamsBuilder().id(id);
    }
}
