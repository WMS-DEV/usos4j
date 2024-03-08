package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegistrationParams(String id) implements UsosParams {

    public static UsosRegistrationParamsBuilder builder(String id) {
        return new UsosRegistrationParamsBuilder().id(id);
    }

}
