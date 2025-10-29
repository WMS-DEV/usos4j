package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuidePagesParams(String pageIds, UsosLanguage lang) implements UsosParams {

    public static UsosGuidePagesParamsBuilder builder(String... pageIds) {
        String ids = FieldSelector.from(pageIds).getSelectedFields();
        return new UsosGuidePagesParamsBuilder().pageIds(ids);
    }
}
