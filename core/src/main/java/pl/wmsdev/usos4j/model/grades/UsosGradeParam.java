package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;
import pl.wmsdev.usos4j.model.user.UsosUser;

@Builder
public record UsosGradeParam(Integer examId, Integer examSessionNumber, Integer userId) implements UsosParams {

    public static UsosGradeParamBuilder builder(Integer examId, Integer examSessionNumber) {
        return new UsosGradeParamBuilder().examId(examId)
                .examSessionNumber(examSessionNumber);
    }
}
