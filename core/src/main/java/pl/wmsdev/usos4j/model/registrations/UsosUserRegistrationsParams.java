package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosUserRegistrationsParams (Boolean activeOnly) implements UsosParams {

    public static UsosUserRegistrationsParamsBuilder builder() {
        return new UsosUserRegistrationsParamsBuilder();
    }
}
