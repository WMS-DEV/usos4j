package pl.wmsdev.usos4j.model.payments;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosInstallmentParams(String installmentPlanId, Integer number) implements UsosParams {

    public static UsosInstallmentParamsBuilder builder(String installmentPlanId, Integer number) {
        return new UsosInstallmentParamsBuilder().installmentPlanId(installmentPlanId).number(number);
    }

}
