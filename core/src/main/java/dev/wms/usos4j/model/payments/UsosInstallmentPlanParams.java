package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosInstallmentPlanParams(String installmentPlanId) implements UsosParams {

    public static UsosInstallmentPlanParamsBuilder builder(String installmentPlanId) {
        return new UsosInstallmentPlanParamsBuilder().installmentPlanId(installmentPlanId);
    }

}