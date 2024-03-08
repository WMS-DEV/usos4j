package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosUserTokenRegistrationsParams(Boolean activeOnly) implements UsosParams {

    public static UsosUserTokenRegistrationsParamsBuilder builder() {
        return new UsosUserTokenRegistrationsParamsBuilder();
    }
}
