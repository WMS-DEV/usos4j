package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosCourseRegistrationRoundsParams(String registrationId) implements UsosParams {

    public static UsosCourseRegistrationRoundsParamsBuilder builder(String registrationId) {
        return new UsosCourseRegistrationRoundsParamsBuilder().registrationId(registrationId);
    }

}
