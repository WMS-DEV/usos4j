package pl.wmsdev.usos4j.model.fac;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosFacultiesSearchParams(UsosLanguage lang, String query, UsosFacultiesSearchVisibility visibility,
                                        Integer num, Integer start) implements UsosParams {

    public static UsosFacultiesSearchParamsBuilder builder(UsosLanguage lang, String query) {
        return new UsosFacultiesSearchParamsBuilder().lang(lang).query(query)
                .visibility(UsosFacultiesSearchVisibility.PUBLIC).num(6).start(0);
    }

}
