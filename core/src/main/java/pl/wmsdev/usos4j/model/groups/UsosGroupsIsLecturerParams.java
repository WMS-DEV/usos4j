package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsIsLecturerParams(Integer courseUnitId, Integer groupNumber,
                                         String userId) implements UsosParams {

    public static UsosGroupsIsLecturerParamsBuilder builder(Integer courseUnitId, Integer groupNumber, String userId) {
        return new UsosGroupsIsLecturerParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber).userId(userId);
    }

}
