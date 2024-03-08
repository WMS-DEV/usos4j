package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultiesSubfacultyDeepParams(String facId) implements UsosParams {

    public static UsosFacultiesSubfacultyDeepParamsBuilder builder(String facId) {
        return new UsosFacultiesSubfacultyDeepParamsBuilder().facId(facId);
    }

}
