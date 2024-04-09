package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCourseSearchParam(UsosLanguage lang, String name, Integer num, Integer start,
                                    String facId, Boolean facDeep) implements UsosParams {

    public static UsosCourseSearchParamBuilder builder(UsosLanguage lang) {
        return new UsosCourseSearchParamBuilder().lang(lang);
    }
}
