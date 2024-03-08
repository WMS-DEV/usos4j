package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTokenRegistrationParams(String id) implements UsosParams {

    public static UsosTokenRegistrationParamsBuilder builder(String id) {
        return new UsosTokenRegistrationParamsBuilder().id(id);
    }

}
