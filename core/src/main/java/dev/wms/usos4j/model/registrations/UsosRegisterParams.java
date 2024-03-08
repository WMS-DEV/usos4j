package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosRegisterParams(String roundId, String courseId, String termId,
                                 String userProgrammeId, String userStageId) implements UsosParams {

    public static UsosRegisterParamsBuilder builder(String roundId, String courseId, String termId) {
        return new UsosRegisterParamsBuilder().roundId(roundId).courseId(courseId).termId(termId);
    }

}
