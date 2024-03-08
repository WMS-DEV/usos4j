package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegistrationRoundParams(String id) implements UsosParams {

    public static UsosRegistrationRoundParamsBuilder builder(String id) {
        return new UsosRegistrationRoundParamsBuilder().id(id);
    }

}
