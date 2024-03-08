package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsIsLecturerWithTokenParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosGroupsIsLecturerWithTokenParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosGroupsIsLecturerWithTokenParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
