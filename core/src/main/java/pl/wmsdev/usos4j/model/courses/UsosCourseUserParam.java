package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseUserParam(Boolean activeTermsOnly) implements UsosParams {
}
