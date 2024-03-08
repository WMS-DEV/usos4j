package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosClassGroupParamsWithToken(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosClassGroupParamsWithTokenBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosClassGroupParamsWithTokenBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}