package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGroupParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosGroupParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosGroupParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
