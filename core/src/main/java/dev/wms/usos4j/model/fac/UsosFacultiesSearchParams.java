package dev.wms.usos4j.model.fac;

import dev.wms.usos4j.model.common.UsosLanguage;
import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultiesSearchParams(UsosLanguage lang, String query, UsosFacultiesSearchVisibility visibility,
                                        int num, int start) implements UsosParams {

    public static UsosFacultiesSearchParamsBuilder builder(UsosLanguage lang, String query) {
        return new UsosFacultiesSearchParamsBuilder().lang(lang).query(query).visibility(UsosFacultiesSearchVisibility.PUBLIC).num(6).start(0);
    }

}
