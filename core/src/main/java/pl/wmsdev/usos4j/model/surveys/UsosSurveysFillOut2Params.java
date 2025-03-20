package pl.wmsdev.usos4j.model.surveys;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

import java.util.Map;

@Builder
public record UsosSurveysFillOut2Params(String surveyId, Map<String, UsosSurveysSimpleQuestion> answers, String comment) implements UsosParams {

    public static UsosSurveysFillOut2ParamsBuilder builder(
            String surveyId, Map<String, UsosSurveysSimpleQuestion> answers) {
        return new UsosSurveysFillOut2ParamsBuilder()
                .surveyId(surveyId)
                .answers(answers);
    }
}
