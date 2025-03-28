package pl.wmsdev.usos4j.model.payments;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosRemittanceParams(String id) implements UsosParams {

    public static UsosRemittanceParamsBuilder builder(String id) {
        return new UsosRemittanceParamsBuilder().id(id);
    }

}