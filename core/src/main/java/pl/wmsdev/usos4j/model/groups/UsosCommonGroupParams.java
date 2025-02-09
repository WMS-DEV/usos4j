package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCommonGroupParams(String userId) implements UsosParams {

    public static UsosCommonGroupParamsBuilder builder(String userId) {
        return new UsosCommonGroupParamsBuilder().userId(userId);
    }

}
