package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosInstallmentParams(String installmentPlanId, Integer number) implements UsosParams {

    public static UsosInstallmentParamsBuilder builder(String installmentPlanId, Integer number) {
        return new UsosInstallmentParamsBuilder().installmentPlanId(installmentPlanId).number(number);
    }

}
