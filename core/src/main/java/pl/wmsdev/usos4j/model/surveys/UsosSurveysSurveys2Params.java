package pl.wmsdev.usos4j.model.surveys;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosSurveysSurveys2Params(String surveyIds) implements UsosParams {

    public static UsosSurveysSurveys2ParamsBuilder builder(String... surveyIds) {
        return new UsosSurveysSurveys2ParamsBuilder()
                .surveyIds(FieldSelector.from(surveyIds).getSelectedFields());
    }
}