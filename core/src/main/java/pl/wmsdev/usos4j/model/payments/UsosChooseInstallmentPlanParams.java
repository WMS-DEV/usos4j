package pl.wmsdev.usos4j.model.payments;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosChooseInstallmentPlanParams(String paymentId, String planId) implements UsosParams {

    public static UsosChooseInstallmentPlanParamsBuilder builder(String paymentId, String planId) {
        return new UsosChooseInstallmentPlanParamsBuilder().paymentId(paymentId).planId(planId);
    }

}
