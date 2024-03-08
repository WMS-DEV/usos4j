package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRemittanceParams(String id) implements UsosParams {

    public static UsosRemittanceParamsBuilder builder(String id) {
        return new UsosRemittanceParamsBuilder().id(id);
    }

}