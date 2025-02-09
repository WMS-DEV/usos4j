package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTokenRegistrationRoundParams(String id) implements UsosParams {

    public static UsosTokenRegistrationRoundParamsBuilder builder(String id) {
        return new UsosTokenRegistrationRoundParamsBuilder().id(id);
    }

}
