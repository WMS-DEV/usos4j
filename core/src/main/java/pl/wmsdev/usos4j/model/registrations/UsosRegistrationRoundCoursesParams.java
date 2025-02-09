package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosRegistrationRoundCoursesParams(String registrationRoundId) implements UsosParams {

    public static UsosRegistrationRoundCoursesParamsBuilder builder(String registrationRoundId) {
        return new UsosRegistrationRoundCoursesParamsBuilder().registrationRoundId(registrationRoundId);
    }

}

