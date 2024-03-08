package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultyRegistrationsParams(String facultyId, Boolean activeOnly, Boolean userRelated) implements UsosParams {

    public static UsosFacultyRegistrationsParamsBuilder builder(String facultyId) {
        return new UsosFacultyRegistrationsParamsBuilder().facultyId(facultyId);
    }

}
