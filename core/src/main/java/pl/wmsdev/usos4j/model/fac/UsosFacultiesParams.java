package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultiesParams(String facIds) implements UsosParams {

    public static UsosFacultiesParams.UsosFacultiesParamsBuilder builder(String... facIds) {
        return new UsosFacultiesParams.UsosFacultiesParamsBuilder().facIds(String.join("|", facIds));
    }

}