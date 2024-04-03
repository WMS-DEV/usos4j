package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosLatestParam(Integer days) implements UsosParams {
}
