package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTokenRegistrationRoundParams (String id) implements UsosParams {

    public static UsosTokenRegistrationRoundParamsBuilder builder(String id) {
        return new UsosTokenRegistrationRoundParamsBuilder().id(id);
    }

}
