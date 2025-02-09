package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosRegistrationRoundParams(String id) implements UsosParams {

    public static UsosRegistrationRoundParamsBuilder builder(String id) {
        return new UsosRegistrationRoundParamsBuilder().id(id);
    }

}
