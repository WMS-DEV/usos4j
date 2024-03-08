package pl.wmsdev.usos4j.model.theses;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesesParams(String thsIds) implements UsosParams {

    public static UsosThesesParamsBuilder builder(String... thsIds) {
        return new UsosThesesParamsBuilder().thsIds(String.join("|", thsIds));
    }

}