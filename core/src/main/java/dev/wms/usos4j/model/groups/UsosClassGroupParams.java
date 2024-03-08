package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosClassGroupParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosClassGroupParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosClassGroupParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
