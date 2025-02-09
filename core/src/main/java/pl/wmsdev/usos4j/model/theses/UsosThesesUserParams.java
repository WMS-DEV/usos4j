package pl.wmsdev.usos4j.model.theses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosThesesUserParams(String userId) implements UsosParams {

    public static UsosThesesUserParamsBuilder builder(String userId) {
        return new UsosThesesUserParamsBuilder().userId(userId);
    }

}