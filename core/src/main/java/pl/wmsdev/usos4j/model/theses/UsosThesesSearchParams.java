package pl.wmsdev.usos4j.model.theses;

import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesesSearchParams(UsosLanguage lang, String query, UsosThesisType type, int start) implements UsosParams {

    public static UsosThesesSearchParamsBuilder builder(UsosLanguage lang) {
        return new UsosThesesSearchParamsBuilder().lang(lang);
    }

}
