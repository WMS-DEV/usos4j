package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosFacultyTokenRegistrationsParams(String facultyId, Boolean activeOnly,
                                                  Boolean userRelated) implements UsosParams {

    public static UsosFacultyTokenRegistrationsParamsBuilder builder(String facultyId) {
        return new UsosFacultyTokenRegistrationsParamsBuilder().facultyId(facultyId);
    }

}
