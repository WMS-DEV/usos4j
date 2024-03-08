package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosPaymentParams(String id, UsosPaymentType type) implements UsosParams {

    public static UsosPaymentParamsBuilder builder(String id) {
        return new UsosPaymentParamsBuilder().id(id);
    }

}
