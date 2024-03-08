package dev.wms.usos4j.model.terms;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTermParams(String termId) implements UsosParams {

    public static UsosTermParamsBuilder builder(String termId) {
        return new UsosTermParamsBuilder().termId(termId);
    }

}
