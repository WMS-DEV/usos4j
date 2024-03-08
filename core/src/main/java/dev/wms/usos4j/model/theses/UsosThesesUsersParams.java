package dev.wms.usos4j.model.theses;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesesUsersParams(String userIds) implements UsosParams {

    public static UsosThesesUsersParamsBuilder builder(String... userIds) {
        return new UsosThesesUsersParamsBuilder().userIds(String.join("|", userIds));
    }

}