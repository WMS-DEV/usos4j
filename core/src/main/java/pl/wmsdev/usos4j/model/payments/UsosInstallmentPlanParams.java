package pl.wmsdev.usos4j.model.payments;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosInstallmentPlanParams(String installmentPlanId) implements UsosParams {

    public static UsosInstallmentPlanParamsBuilder builder(String installmentPlanId) {
        return new UsosInstallmentPlanParamsBuilder().installmentPlanId(installmentPlanId);
    }

}