package pl.wmsdev.usos4j.model.theses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosThesesUsersParams(String userIds) implements UsosParams {

    public static UsosThesesUsersParamsBuilder builder(String... userIds) {
        return new UsosThesesUsersParamsBuilder().userIds(String.join("|", userIds));
    }

}