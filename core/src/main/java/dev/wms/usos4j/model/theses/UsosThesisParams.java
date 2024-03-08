package dev.wms.usos4j.model.theses;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesisParams(String thsId) implements UsosParams {

    public static UsosThesisParamsBuilder builder(String thsId) {
        return new UsosThesisParamsBuilder().thsId(thsId);
    }

}