package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosClassGroupParamsWithToken(Integer courseUnitId, Integer groupNumber) implements UsosParams {

    public static UsosClassGroupParamsWithTokenBuilder builder(Integer courseUnitId, Integer groupNumber) {
        return new UsosClassGroupParamsWithTokenBuilder().courseUnitId(courseUnitId).groupNumber(groupNumber);
    }

}