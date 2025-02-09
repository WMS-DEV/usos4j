package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGroupsIsLecturerWithTokenParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosGroupsIsLecturerWithTokenParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosGroupsIsLecturerWithTokenParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
