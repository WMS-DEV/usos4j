package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRankingParams(String rankingCode) implements UsosParams {

    public static UsosRankingParamsBuilder builder(String rankingCode) {
        return new UsosRankingParamsBuilder().rankingCode(rankingCode);
    }

}

