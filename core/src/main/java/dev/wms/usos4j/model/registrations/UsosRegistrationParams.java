package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegistrationParams(String id) implements UsosParams {

    public static UsosRegistrationParamsBuilder builder(String id) {
        return new UsosRegistrationParamsBuilder().id(id);
    }

}
