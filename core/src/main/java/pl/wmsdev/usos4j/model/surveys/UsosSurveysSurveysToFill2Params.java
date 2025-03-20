package pl.wmsdev.usos4j.model.surveys;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosSurveysSurveysToFill2Params(Boolean includeFilledOut) implements UsosParams {
}