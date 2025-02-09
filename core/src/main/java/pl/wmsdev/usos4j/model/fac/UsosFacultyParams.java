package pl.wmsdev.usos4j.model.fac;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosFacultyParams(String facId) implements UsosParams {

    public static UsosFacultyParamsBuilder builder(String facId) {
        return new UsosFacultyParamsBuilder().facId(facId);
    }

}
