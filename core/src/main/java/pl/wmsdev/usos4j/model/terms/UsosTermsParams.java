package pl.wmsdev.usos4j.model.terms;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTermsParams(String termIds) implements UsosParams {

    public static UsosTermsParamsBuilder builder(String... termIds) {
        return new UsosTermsParamsBuilder().termIds(String.join("|", termIds));
    }

}