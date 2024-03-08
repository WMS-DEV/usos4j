package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosChooseInstallmentPlanParams(String paymentId, String planId) implements UsosParams {

    public static UsosChooseInstallmentPlanParamsBuilder builder(String paymentId, String planId) {
        return new UsosChooseInstallmentPlanParamsBuilder().paymentId(paymentId).planId(planId);
    }

}
