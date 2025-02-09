package pl.wmsdev.usos4j.model.theses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosThesesSearchParams(UsosLanguage lang, String query, UsosThesisType type,
                                     Integer start) implements UsosParams {

    public static UsosThesesSearchParamsBuilder builder(UsosLanguage lang) {
        return new UsosThesesSearchParamsBuilder().lang(lang);
    }

}
