package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosAllocationPeriodsParam(String allocationPeriodIds) implements UsosParams {
    public static UsosAllocationPeriodsParamBuilder builder(String... allocationPeriodIds){
        return new UsosAllocationPeriodsParamBuilder().allocationPeriodIds(
                FieldSelector.from(allocationPeriodIds).getSelectedFields());
    }
}

