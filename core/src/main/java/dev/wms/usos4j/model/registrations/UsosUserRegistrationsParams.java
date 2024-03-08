package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosUserRegistrationsParams (Boolean activeOnly) implements UsosParams {

    public static UsosUserRegistrationsParamsBuilder builder() {
        return new UsosUserRegistrationsParamsBuilder();
    }
}
