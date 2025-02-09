package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradesExamParam(Integer examId, Integer userId) implements UsosParams {

    public static UsosGradesExamParamBuilder builder(Integer examId) {
        return new UsosGradesExamParamBuilder().examId(examId);
    }
}
