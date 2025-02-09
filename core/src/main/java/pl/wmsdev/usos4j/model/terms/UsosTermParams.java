package pl.wmsdev.usos4j.model.terms;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTermParams(String termId) implements UsosParams {

    public static UsosTermParamsBuilder builder(String termId) {
        return new UsosTermParamsBuilder().termId(termId);
    }

}
