package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTokenRegistrationParams(String id) implements UsosParams {

    public static UsosTokenRegistrationParamsBuilder builder(String id) {
        return new UsosTokenRegistrationParamsBuilder().id(id);
    }

}
