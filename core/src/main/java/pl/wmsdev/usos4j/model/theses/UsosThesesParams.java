package pl.wmsdev.usos4j.model.theses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosThesesParams(String thsIds) implements UsosParams {

    public static UsosThesesParamsBuilder builder(String... thsIds) {
        return new UsosThesesParamsBuilder().thsIds(String.join("|", thsIds));
    }

}