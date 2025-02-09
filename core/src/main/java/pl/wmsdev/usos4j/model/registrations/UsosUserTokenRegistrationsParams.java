package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosUserTokenRegistrationsParams(Boolean activeOnly) implements UsosParams {

    public static UsosUserTokenRegistrationsParamsBuilder builder() {
        return new UsosUserTokenRegistrationsParamsBuilder();
    }
}
