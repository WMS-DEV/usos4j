package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosGroupParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosGroupParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
