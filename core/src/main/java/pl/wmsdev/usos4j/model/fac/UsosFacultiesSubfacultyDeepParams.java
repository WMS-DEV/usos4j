package pl.wmsdev.usos4j.model.fac;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosFacultiesSubfacultyDeepParams(String facId) implements UsosParams {

    public static UsosFacultiesSubfacultyDeepParamsBuilder builder(String facId) {
        return new UsosFacultiesSubfacultyDeepParamsBuilder().facId(facId);
    }

}
