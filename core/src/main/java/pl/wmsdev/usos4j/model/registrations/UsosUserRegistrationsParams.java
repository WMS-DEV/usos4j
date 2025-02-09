package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosUserRegistrationsParams(Boolean activeOnly) implements UsosParams {

    public static UsosUserRegistrationsParamsBuilder builder() {
        return new UsosUserRegistrationsParamsBuilder();
    }
}
