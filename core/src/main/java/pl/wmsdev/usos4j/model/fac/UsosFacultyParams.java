package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultyParams(String facId) implements UsosParams {

    public static UsosFacultyParamsBuilder builder(String facId) {
        return new UsosFacultyParamsBuilder().facId(facId);
    }

}
