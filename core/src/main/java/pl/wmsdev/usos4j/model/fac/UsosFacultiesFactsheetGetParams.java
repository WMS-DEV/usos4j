package pl.wmsdev.usos4j.model.fac;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultiesFactsheetGetParams(String facId) implements UsosParams {

    public static UsosFacultiesFactsheetGetParamsBuilder builder(String facId) {
        return new UsosFacultiesFactsheetGetParamsBuilder().facId(facId);
    }

}