package pl.wmsdev.usos4j.model.plctests;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosPlacementTestsResultParams(String testId) implements UsosParams {

    public static UsosPlacementTestsResultParams.UsosPlacementTestsResultParamsBuilder builder(String testId) {
        return new UsosPlacementTestsResultParamsBuilder().testId(testId);
    }
}
