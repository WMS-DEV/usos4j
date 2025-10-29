package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuideGuideParams(UsosLanguage lang) implements UsosParams {
    public static UsosGuideGuideParamsBuilder builder(UsosLanguage lang) {
        return new UsosGuideGuideParamsBuilder().lang(lang);
    }
}
