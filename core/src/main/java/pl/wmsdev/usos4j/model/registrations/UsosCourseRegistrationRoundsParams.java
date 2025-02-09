package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseRegistrationRoundsParams(String registrationId) implements UsosParams {

    public static UsosCourseRegistrationRoundsParamsBuilder builder(String registrationId) {
        return new UsosCourseRegistrationRoundsParamsBuilder().registrationId(registrationId);
    }

}
