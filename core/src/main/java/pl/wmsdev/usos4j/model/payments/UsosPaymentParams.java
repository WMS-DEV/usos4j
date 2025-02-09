package pl.wmsdev.usos4j.model.payments;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosPaymentParams(String id, UsosPaymentType type) implements UsosParams {

    public static UsosPaymentParamsBuilder builder(String id) {
        return new UsosPaymentParamsBuilder().id(id);
    }

}
