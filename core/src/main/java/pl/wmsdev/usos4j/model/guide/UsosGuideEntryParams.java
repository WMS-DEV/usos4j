package pl.wmsdev.usos4j.model.guide;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGuideEntryParams(String entryId, UsosLanguage lang) implements UsosParams {

    public static UsosGuideEntryParamsBuilder builder(String entryId) {
        return new UsosGuideEntryParamsBuilder().entryId(entryId);
    }
}
