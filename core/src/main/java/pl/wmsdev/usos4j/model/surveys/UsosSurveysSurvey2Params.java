package pl.wmsdev.usos4j.model.surveys;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosSurveysSurvey2Params(String surveyId) implements UsosParams {

    public static UsosSurveysSurvey2ParamsBuilder builder(String surveyId) {
        return new UsosSurveysSurvey2ParamsBuilder()
                .surveyId(surveyId);
    }
}
