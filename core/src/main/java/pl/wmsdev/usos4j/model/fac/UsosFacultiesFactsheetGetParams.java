package pl.wmsdev.usos4j.model.fac;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosFacultiesFactsheetGetParams(String facId) implements UsosParams {

    public static UsosFacultiesFactsheetGetParamsBuilder builder(String facId) {
        return new UsosFacultiesFactsheetGetParamsBuilder().facId(facId);
    }

}