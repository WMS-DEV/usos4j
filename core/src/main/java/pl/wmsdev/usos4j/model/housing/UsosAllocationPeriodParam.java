package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosAllocationPeriodParam(String allocationPeriodId) implements UsosParams {
    public static UsosAllocationPeriodParamBuilder builder(String allocationPeriodId) {
        return new UsosAllocationPeriodParamBuilder().allocationPeriodId(allocationPeriodId);
    }
}
