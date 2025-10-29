package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuideEntriesParams(String entryIds) implements UsosParams {

    public static UsosGuideEntriesParamsBuilder builder(String... entryIds) {
        String ids = FieldSelector.from(entryIds).getSelectedFields();
        return new UsosGuideEntriesParamsBuilder().entryIds(ids);
    }
}
