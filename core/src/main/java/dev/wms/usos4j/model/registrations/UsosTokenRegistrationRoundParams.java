package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTokenRegistrationRoundParams (String id) implements UsosParams {

    public static UsosTokenRegistrationRoundParamsBuilder builder(String id) {
        return new UsosTokenRegistrationRoundParamsBuilder().id(id);
    }

}
