package pl.wmsdev.usos4j.model.theses;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesesUserParams(String userId) implements UsosParams {

    public static UsosThesesUserParamsBuilder builder(String userId) {
        return new UsosThesesUserParamsBuilder().userId(userId);
    }

}