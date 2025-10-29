package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuidePageParams(String pageId, UsosLanguage lang) implements UsosParams {

    public static UsosGuidePageParamsBuilder builder(String pageId) {
        return new UsosGuidePageParamsBuilder().pageId(pageId);
    }
}
