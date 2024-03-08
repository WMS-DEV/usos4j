package pl.wmsdev.usos4j.model.theses;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosThesesSearchHistoryAffectParams(String thsId) implements UsosParams {

    public static UsosThesesSearchHistoryAffectParamsBuilder builder(String thsId) {
        return new UsosThesesSearchHistoryAffectParamsBuilder().thsId(thsId);
    }

}