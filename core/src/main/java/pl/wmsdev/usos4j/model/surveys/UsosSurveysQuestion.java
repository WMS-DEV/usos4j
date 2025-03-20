package pl.wmsdev.usos4j.model.surveys;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosSurveysQuestion(
        String id,
        String number,
        Integer level,
        UsosLocalizedString displayTextHtml,
        List<UsosSurveysAnswer> possibleAnswers,
        Boolean allowComment,
        Integer commentLength
) {
}


