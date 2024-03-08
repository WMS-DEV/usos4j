package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegistrationRoundCoursesParams(String registrationRoundId) implements UsosParams {

    public static UsosRegistrationRoundCoursesParamsBuilder builder(String registrationRoundId) {
        return new UsosRegistrationRoundCoursesParamsBuilder().registrationRoundId(registrationRoundId);
    }

}

