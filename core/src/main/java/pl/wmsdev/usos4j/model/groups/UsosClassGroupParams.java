package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosClassGroupParams(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosClassGroupParamsBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosClassGroupParamsBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}
