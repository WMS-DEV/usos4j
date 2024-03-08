package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosCommonGroupParams(String userId) implements UsosParams {

    public static UsosCommonGroupParamsBuilder builder(String userId) {
        return new UsosCommonGroupParamsBuilder().userId(userId);
    }

}
