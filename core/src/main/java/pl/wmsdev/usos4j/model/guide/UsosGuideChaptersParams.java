package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuideChaptersParams(String chapterIds, UsosLanguage lang) implements UsosParams {

    public static UsosGuideChaptersParamsBuilder builder(String... chapterIds) {
        String ids = FieldSelector.from(chapterIds).getSelectedFields();
        return new UsosGuideChaptersParamsBuilder().chapterIds(ids);
    }
}
