package pl.wmsdev.usos4j.model.registrations;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosUnregisterParams(String roundId, String courseId, String termId) implements UsosParams {

    public static UsosUnregisterParamsBuilder builder(String roundId, String courseId, String termId) {
        return new UsosUnregisterParamsBuilder().roundId(roundId).courseId(courseId).termId(termId);
    }
}
