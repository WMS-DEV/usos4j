package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGroupsIsParticipantParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosGroupsIsParticipantParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosGroupsIsParticipantParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
