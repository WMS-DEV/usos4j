package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosRankingParams(String rankingCode) implements UsosParams {

    public static UsosRankingParamsBuilder builder(String rankingCode) {
        return new UsosRankingParamsBuilder().rankingCode(rankingCode);
    }

}

