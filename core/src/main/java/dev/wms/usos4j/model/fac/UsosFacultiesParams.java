package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultiesParams(String facIds) implements UsosParams {

    public static UsosFacultiesParams.UsosFacultiesParamsBuilder builder(String... facIds) {
        return new UsosFacultiesParams.UsosFacultiesParamsBuilder().facIds(String.join("|", facIds));
    }

}