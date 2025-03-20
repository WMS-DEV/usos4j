package pl.wmsdev.usos4j.model.surveys;

import java.util.List;

public record UsosSurveysSimpleQuestion(
        List<String> answers,
        String comment
) {
}


