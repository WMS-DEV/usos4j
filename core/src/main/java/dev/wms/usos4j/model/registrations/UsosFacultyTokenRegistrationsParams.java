package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultyTokenRegistrationsParams(String facultyId, Boolean activeOnly, Boolean userRelated) implements UsosParams {

    public static UsosFacultyTokenRegistrationsParamsBuilder builder(String facultyId) {
        return new UsosFacultyTokenRegistrationsParamsBuilder().facultyId(facultyId);
    }

}
